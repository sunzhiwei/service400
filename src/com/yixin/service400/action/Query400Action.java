package com.yixin.service400.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400File;
import com.yixin.service400.util.Conf;
import com.yixin.service400.util.PageUtil;
import com.yixin.service400.util.Pagination;
import com.yixin.service400.util.StringUtil;

@Controller
@Scope("prototype")
public class Query400Action extends ModelDrivenBaseAction<TService400File> {
	static Logger log = Logger.getLogger(Query400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	private String path;
	private TService400File tsf = new TService400File();
	
	public String queryHis(){
		return "queryHis";
	}
	public String closeLoopByID() throws Exception{
		try {
			tsf = query400Service.closeLoop(tsf.getId());
			workGroupService.updateCloseLoop(tsf.getSendperson());
			ivr400Service.updateCloseLoop(tsf.getPhonenum());
			log.info(this.getCurrentUser().getUsername()+"对工作组和IVR闭环!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"对工作组和IVR闭环失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "closeLoopByID";
	}
	private List<TService400File> list = new ArrayList<TService400File>();
	private String time1;
	private String time2;
	private String time3;
	private String time4;
	public String closeLoop() {
		return "closeLoop";
	}
	public void download(){
		try {
			tsf = query400Service.getTSfByID(tsf.getId());
			String fileName = tsf.getFileName();
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			response.setHeader("content-disposition", "attachment;filename="+fileName);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(new File(Conf.getValue("download.file")+tsf.getFilepath()));
			byte [] by = new byte [102400];
			int length = 0;
			while ( ( length = in.read(by) ) != -1 ) {
			      out.write(by, 0, length);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"下载失败。");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public String queryPageJson() throws Exception{
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		try {
			map=setMapValue(map);
			int totalCounts = query400Service.getTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage,pageSize,totalCounts);
			//查询数据集
			Map map2 = PageUtil.getMap(page);
			map2=setMapValue(map2);
			List list = query400Service.queryPageByParams(map2);
			List fileList=new ArrayList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				TService400File file=(TService400File) it.next();
				Map fileMap=new HashMap();
				fileMap.put("id", file.getId());
				fileMap.put("sendperson", file.getSendperson());
				fileMap.put("phonenum", file.getPhonenum());
				fileMap.put("sendtime", file.getSendtime());
				fileMap.put("type", file.getTypeString());
				fileMap.put("filepathURL", file.getFilepathURL());
				fileList.add(fileMap);
			}
			page.setRows(fileList);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername()+"查看400号码，IVR及工作组维护申请历史记录列表！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"查看400号码，IVR及工作组维护申请历史记录列表失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}
	
	@SuppressWarnings("unchecked")
	private Map setMapValue(Map map) throws Exception{
		map.put("sendperson", StringUtil.addLikeOperBoth(request.getParameter("sendperson")));
		map.put("type", request.getParameter("type"));
		map.put("phonenum", StringUtil.addLikeOperBoth(request.getParameter("phonenum")));
		map.put("sendstarttime", request.getParameter("sendstarttime"));
		map.put("sendendtime", request.getParameter("sendendtime"));
		map.put("closestarttime", request.getParameter("closestarttime"));
		map.put("closeendtime", request.getParameter("closeendtime"));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public String queryCLPageJson() throws Exception{
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		try {
			map=setCLMapValue(map);
			int totalCounts = query400Service.getCLTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage,pageSize,totalCounts);
			//查询数据集
			Map map2 = PageUtil.getMap(page);
			map2=setCLMapValue(map2);
			List list = query400Service.queryCLPageByParams(map2);
			List fileList=new ArrayList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				TService400File file=(TService400File) it.next();
				Map fileMap=new HashMap();
				fileMap.put("id", file.getId());
				fileMap.put("sendperson", file.getSendperson());
				fileMap.put("sendtime", file.getSendtime());
				fileMap.put("type", file.getTypeString());
				fileMap.put("filepathURL", file.getFilepathURL());
				fileList.add(fileMap);
			}
			page.setRows(fileList);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername()+"查看IVR及工作组维护申请记录列表！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"查看IVR及工作组维护申请记录列表失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}

	@SuppressWarnings("unchecked")
	private Map setCLMapValue(Map map) throws Exception{
		map.put("sendperson", currentUser.getUsername());
		map.put("type", request.getParameter("type"));
		map.put("sendstarttime", request.getParameter("sendstarttime"));
		map.put("sendendtime", request.getParameter("sendendtime"));
		return map;
	}
	/*******************************************/

	public String getPath() {
		if (path == null || path.equals("")) {
			return request.getContextPath();
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<TService400File> getList() {
		return list;
	}

	public void setList(List<TService400File> list) {
		this.list = list;
	}

	public TService400File getTsf() {
		return tsf;
	}

	public void setTsf(TService400File tsf) {
		this.tsf = tsf;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTime3() {
		return time3;
	}
	public void setTime3(String time3) {
		this.time3 = time3;
	}
	public String getTime4() {
		return time4;
	}
	public void setTime4(String time4) {
		this.time4 = time4;
	}
}
