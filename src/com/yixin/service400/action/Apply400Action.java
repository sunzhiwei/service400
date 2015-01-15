package com.yixin.service400.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400File;
import com.yixin.service400.util.Conf;
import com.yixin.service400.util.DateUtil;
import com.yixin.service400.util.MailService;
import com.yixin.service400.util.MailUtil;
import com.yixin.service400.util.PageUtil;
import com.yixin.service400.util.Pagination;
import com.yixin.service400.util.StatusUtil;
import com.yixin.service400.util.ZipFileUtil;

@Controller
@Scope("prototype")
public class Apply400Action extends
		ModelDrivenBaseAction<TService400Applicationform> {
	static Logger log = Logger.getLogger(Apply400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	private String path;
	private TService400Applicationform ts = new TService400Applicationform();
	private List<TService400Applicationform> tsList = new ArrayList<TService400Applicationform>();

	private List<TService400> list = new ArrayList<TService400>();

	private File[] tsfile;
	private String[] tsfileFileName;
	private String[] tsfileContentType;

	public String clsLoop() throws Exception{
		try {
			String url = Conf.getValue("title.file");
			ts = apply400Service.getTService400ApplicationformById(ts.getId());
			String str = ts.getFilepath();
			String[] arr = str.split(";");
			String newPath = DateUtil.getTime("yyyy-MM-dd");
			String xxPath = newPath+File.separator+"400号码申请文件"+DateUtil.getTime("yyyy-MM-ddHHmmss")+".zip";
			ZipFileUtil.zipFile(url + xxPath, arr);
			TService400File file400 = new TService400File();
			file400.setSendperson(currentUser.getUsername());
			file400.setSendtime(newPath);
			file400.setPhonenum(ts.getPhonenum()+"");
			file400.setFilepath(xxPath);
			file400.setStatus(StatusUtil.CONST_FILE_STATUS_BIHUAN); // 闭环
			file400.setType(TService400File.STATUS_TYPE_SHENQING);
			fileService.save(file400);
			apply400Service.closeLoop(ts);
			log.info(currentUser.getUsername() + "对400号码申请进行闭环!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "对400号码申请闭环失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "clsLoop";
	}

	private int flag;

	public String conAppSup() throws Exception{
		try {
			String paths = "";
			String newPath = DateUtil.getTime("yyyy-MM-dd");
			String url = Conf.getValue("title.file");
			if (tsfile != null) {
				for (int i = 0; i < tsfile.length; i++) {
					File file = new File(url + File.separator + newPath + File.separator + tsfileFileName[i]);
					paths = paths + newPath + File.separator + file.getName() + ";";
					FileUtils.copyFile(tsfile[i], file);
				}
			}
			ts.setFilepath(paths);
			apply400Service.supApply(ts);
			if (flag==1) {
				apply400Service.closeLoop(ts);
			}
			log.info(currentUser.getUsername() + "对400号码申请进行信息补充!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "对400号码申请信息补充失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "conAppSup";
	}

	public String conApp() throws Exception{
		try {
			ts = apply400Service.getTService400ApplicationformById(ts.getId());
			log.info(currentUser.getUsername() + "打开400号码补充申请页面!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "打开400号码补充申请页面失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "conApp";
	}

	public String check400() throws Exception{
		try {
			tsList = apply400Service.getTService400ApplicationformList();
			log.info(currentUser.getUsername() + "查询未闭环的400号码申请表!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "查询未闭环的400号码申请表失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "check400";
	}

	/*************************** 400号码申请 **************************************/
	private String notes = "";

	public String apply400() throws Exception{
		try {
			notes = notes400Service.getFirstNotes();
			list = num400Service.get400List(new TService400());
			log.info("查询400申请公告及未被申请的400号码列表!");
		} catch (Exception e) {
			log.error("查询400申请公告及未被申请的400号码列表失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "apply400";
	}

	public String add400Apply() {
		flag = -1;
		try {
			num400Service.setApply(ts.getService400Id());
			ts.setStatus(TService400Applicationform.STATUS_WSH);
			ts.setApplicationtime(DateUtil.getTime());
			File file = com.yixin.service400.util.FileUtils.getDistFileByType(0);
			String titlepath = Conf.getValue("title.file");
			String filepath = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(titlepath)+ titlepath.length() + 1,file.getAbsolutePath().length());
			ts.setFilepath(filepath+";");
			MailUtil.sendApplyMail(ts, file);
			apply400Service.addApply(ts);
			//TODO ts.getId()
//			HashMap<String,Object> map = new HashMap<String,Object>();
//			map.put("id", ts.getId().longValue());
//			if(!MailService.sendMail(MailService.MAIL_TYPE_APPLY,map)){
//				log.info("邮件发送失败。");
//				flag = 1;
//			}
			log.info("申请400号码并发送邮件！");
		} catch (Exception e) {
			log.error("申请400号码并发送邮件失败！");
			e.printStackTrace();
			flag = 1;
		}
		return "emailOK";
	}

	@SuppressWarnings("unchecked")
	public String queryPageJson() throws Exception {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		try {
			map = setMapValue(map);
			int totalCounts = apply400Service.getTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage, pageSize, totalCounts);
			// 查询数据集
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(map2);
			List list = apply400Service.queryPageByParams(map2);
			List applicationFormList = new ArrayList();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				TService400Applicationform applicationform = (TService400Applicationform) it
						.next();
				Map applicationformMap = new HashMap();
				applicationformMap.put("id", applicationform.getId());
				applicationformMap.put("applicationperson",
						applicationform.getApplicationperson());
				applicationformMap.put("phonenum",
						applicationform.getPhonenum());
				applicationformMap.put("opentime",
						applicationform.getOpentime());
				applicationformMap.put("applicationtime",
						applicationform.getApplicationtime());
				applicationformMap.put("company", applicationform.getCompany());
				applicationformMap.put("department",
						applicationform.getDepartment());
				applicationformMap.put("status", applicationform.getStatus());
				applicationFormList.add(applicationformMap);
			}
			page.setRows(applicationFormList);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername() + "查看400号码审核列表！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername() + "查看400号码审核列表失败", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}

	@SuppressWarnings("unchecked")
	private Map setMapValue(Map map) throws Exception {
		map.put("applicationperson", request.getParameter("applicationperson"));
		map.put("phonenum", request.getParameter("phonenum"));
		map.put("company", request.getParameter("company"));
		map.put("department", request.getParameter("department"));
		map.put("startapplicationtime", request.getParameter("startapplicationtime"));
		map.put("endapplicationtime", request.getParameter("endapplicationtime"));
		return map;
	}

	/*******************************************/

	public TService400Applicationform getTs() {
		return ts;
	}

	public void setTs(TService400Applicationform ts) {
		this.ts = ts;
	}

	public List<TService400> getList() {
		return list;
	}

	public void setList(List<TService400> list) {
		this.list = list;
	}

	public String getPath() {
		if (path == null || path.equals("")) {
			return request.getContextPath();
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<TService400Applicationform> getTsList() {
		return tsList;
	}

	public void setTsList(List<TService400Applicationform> tsList) {
		this.tsList = tsList;
	}

	public File[] getTsfile() {
		return tsfile;
	}

	public void setTsfile(File[] tsfile) {
		this.tsfile = tsfile;
	}

	public String[] getTsfileFileName() {
		return tsfileFileName;
	}

	public void setTsfileFileName(String[] tsfileFileName) {
		this.tsfileFileName = tsfileFileName;
	}

	public String[] getTsfileContentType() {
		return tsfileContentType;
	}

	public void setTsfileContentType(String[] tsfileContentType) {
		this.tsfileContentType = tsfileContentType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
