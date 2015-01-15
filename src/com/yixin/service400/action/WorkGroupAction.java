package com.yixin.service400.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400File;
import com.yixin.service400.bean.TService400Workgroup;
import com.yixin.service400.util.Conf;
import com.yixin.service400.util.ExportUtils;
import com.yixin.service400.util.FileUtils;
import com.yixin.service400.util.MailUtil;
import com.yixin.service400.util.PageUtil;
import com.yixin.service400.util.Pagination;
import com.yixin.service400.util.StatusUtil;

@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
public class WorkGroupAction extends ModelDrivenBaseAction<TService400Workgroup> {
	
	private static final long serialVersionUID = -5016417664728022461L;

	static Logger log = Logger.getLogger(WorkGroupAction.class);

	private TService400Workgroup  workgroup;
	
	private String workOverFlow=""; // 工作时溢出组(组名逗号分隔)
	
	private String noworkOverFlow=""; // 非工作时溢出组
	
	private String allworkgroup=""; // 所有的工作组
	
	private String allnoworkflowgroup="";
	
	private String workgroupname=""; // 效验数据库组名重复
	
	private Long workgroupid;
	
	public void groupMessage() throws Exception{
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			List<TService400Workgroup> list = workGroupService.getWorkGroupList();
			StringBuffer sb = new StringBuffer();
			for(TService400Workgroup ts : list){
				sb.append("<option selected='selected' value='"+ts.getId()+"'>"+ts.getName()+"</option>");
			}
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
			out.flush();
			log.info(this.getCurrentUser().getUsername()+"获取所有可用工作组");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"获取所有可用工作组失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String queryPageJson() throws Exception{
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		try {
			map = setMapValue(map);
			int totalCounts = workGroupService.getTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage,pageSize,totalCounts);
			//查询数据集
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(map2);
			List list = workGroupService.queryPageByParams(map2);
			List workGroupList=new ArrayList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				TService400Workgroup tService400Workgroup=(TService400Workgroup) it.next();
				Map workgroupMap=new HashMap();
				workgroupMap.put("id", tService400Workgroup.getId());
				workgroupMap.put("name", tService400Workgroup.getName());
				workgroupMap.put("workweek", tService400Workgroup.getStartweek()+"至"+tService400Workgroup.getEndweek());
				workgroupMap.put("worktime", tService400Workgroup.getStarttime()+"-"+tService400Workgroup.getEndtime());
				workgroupMap.put("adapternum", tService400Workgroup.getAdapternum());
				workgroupMap.put("noworkvoice", tService400Workgroup.getNoworkvoice());
				workgroupMap.put("workoverflowgroup", tService400Workgroup.getWorkoverflowGroup());
				workgroupMap.put("noworkoverflowgroup", tService400Workgroup.getNoworkoverflowGroup());
				workgroupMap.put("busytone", tService400Workgroup.getBusyTone());
				int count=workGroupService.getCountoverflowgroup(tService400Workgroup);
				workgroupMap.put("count", count);
				if(tService400Workgroup.getNoworkisoverflow()!=null)
				   workgroupMap.put("noworkisoverflow", StatusUtil.getStatusValue(tService400Workgroup.getNoworkisoverflow().intValue()));
				workGroupList.add(workgroupMap);
			}
			page.setRows(workGroupList);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername()+"查看工作组列表！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"查看工作组列表失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}
	
	@SuppressWarnings("rawtypes")
	public void exportExcel() throws Exception {
		response.reset();
		int currentPage = 1;
		int pageSize = 100;
		Map map = new HashMap();
		map = setMapValue(map);
		int totalCounts = workGroupService.getTotalCountsByParams(map);
		Pagination page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集
		Map map2 = PageUtil.getMap(page);
		map2 = setMapValue(map2);
		List list = workGroupService.queryPageByParams(map2);
		List workGroupList=new ArrayList();
		Iterator it=list.iterator();
		while(it.hasNext()){
			TService400Workgroup tService400Workgroup=(TService400Workgroup) it.next();
			Map workgroupMap=new HashMap();
//			workgroupMap.put("id", tService400Workgroup.getId());
			workgroupMap.put("name", tService400Workgroup.getName());
			workgroupMap.put("workweek", tService400Workgroup.getStartweek()+"至"+tService400Workgroup.getEndweek());
			workgroupMap.put("worktime", tService400Workgroup.getStarttime()+"-"+tService400Workgroup.getEndtime());
			workgroupMap.put("adapternum", tService400Workgroup.getAdapternum());
			workgroupMap.put("noworkvoice", tService400Workgroup.getNoworkvoice());
			workgroupMap.put("workoverflowgroup", tService400Workgroup.getWorkoverflowGroup());
			workgroupMap.put("noworkoverflowgroup", tService400Workgroup.getNoworkoverflowGroup());
			workgroupMap.put("busytone", tService400Workgroup.getBusyTone());
			int count=workGroupService.getCountoverflowgroup(tService400Workgroup);
			workgroupMap.put("count", count);
			if(tService400Workgroup.getNoworkisoverflow()!=null)
			   workgroupMap.put("noworkisoverflow", StatusUtil.getStatusValue(tService400Workgroup.getNoworkisoverflow().intValue()));
			workGroupList.add(workgroupMap);
		}
		// 测试poi导出查询结果到excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		String[] headers = { "工作组名称", "工作周", "工作时间", "外呼显示号码", "非工作时语音", "工作时间溢出组", "非工作时间溢出组", "工作时忙音", "非工作是是否溢出 " };
		String[] fields = { "name", "workweek", "worktime", 
				"adapternum", "noworkvoice", "workoverflowGroup",
				"noworkoverflowGroup", "busyTone", "noworkisoverflow" };
		ExportUtils.exportExcel("工作组数据", headers, fields, workGroupList, workbook);
		String agent = request.getHeader("User-Agent");
		try {
			boolean isFireFox = (agent != null && agent.toLowerCase().indexOf(
					"firefox") != -1);
			if (isFireFox) {
				response.addHeader(
						"Content-Disposition",
						"attachment; filename*="
								+ URLEncoder.encode("工作组报表", "utf-8") + ".xls");
			} else {
				response.addHeader(
						"Content-Disposition",
						"attachment; filename=\""
								+ URLEncoder.encode("工作组报表", "utf-8") + ".xls"
								+ "\"");
			}
		} catch (Exception e) {
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode("工作组报表", "utf-8") + ".xls" + "\"");
		}
		response.setContentType("application/vnd.ms-excel");
		workbook.write(response.getOutputStream());
	}
	
	private Map setMapValue(Map map) throws Exception{
		map.put("workgroupname", request.getParameter("workgroupname"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("workoverflowgroup", request.getParameter("workoverflowgroup"));
		map.put("noworkoverflowgroup", request.getParameter("noworkoverflowgroup"));
		return map;
	}
	
	public String edit() throws Exception{
		List list;
		List list1;
		try {
			workgroup=workGroupService.getById(workgroup.getId());
			list=workGroupService.getAllWorkGroupExceptMe(workgroup.getId());
			list1=workGroupService.getNoWorkFlowGroupExceptMe(workgroup.getId());
			for(int i=0;i<list.size();i++){
				allworkgroup+=(String)list.get(i)+",";
			}
			if(allworkgroup!=null && !("").equals(allworkgroup)){
				allworkgroup=allworkgroup.substring(0,allworkgroup.length()-1);
			}
			for(int i=0;i<list1.size();i++){
			    allnoworkflowgroup+=(String)list1.get(i)+",";
			}
			if(allnoworkflowgroup!=null && !("").equals(allnoworkflowgroup)){
				allnoworkflowgroup=allnoworkflowgroup.substring(0,allnoworkflowgroup.length()-1);
			}
            log.info(this.getCurrentUser().getUsername()+"打开编辑工作组页面！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"打开编辑工作组页面失败！",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "editUI";
	}
	
	public String update() throws Exception{
		try {
			TService400Workgroup workgroup_new=workGroupService.getById(workgroup.getId());
			if(workgroup_new.getStatus().intValue()!=StatusUtil.CONST_WORKGROUP_STATUS_ADD.intValue()){  // 若不是新增状态，该改为什么状态就改为什么状态
				workgroup_new.setStatus(StatusUtil.CONST_WORKGROUP_STATUS_UPDATE); // 工作组变为修改状态
			}
			workgroup_new.setUsername(currentUser.getUsername());
			workgroup_new.setName(workgroup.getName());
			workgroup_new.setAdapternum(workgroup.getAdapternum());
			workgroup_new.setStartweek(workgroup.getStartweek());
			workgroup_new.setEndweek(workgroup.getEndweek());
			workgroup_new.setStarttime(workgroup.getStarttime());
			workgroup_new.setEndtime(workgroup.getEndtime());
			workgroup_new.setWorkoverflowGroup(workgroup.getWorkoverflowGroup());
			// 解决非工作溢出组有", "值入库
			String str=workgroup.getNoworkoverflowGroup();
			if (str.indexOf(",") != -1)
				workgroup_new.setNoworkoverflowGroup(str.substring(0, str.indexOf(",")));
			else
				workgroup_new.setNoworkoverflowGroup(workgroup.getNoworkoverflowGroup());
			workgroup_new.setNoworkvoice(workgroup.getNoworkvoice());
			workgroup_new.setNoworkisoverflow(workgroup.getNoworkisoverflow());
			workgroup_new.setBusyTone(workgroup.getBusyTone());
			workGroupService.update(workgroup_new);
			log.info(this.getCurrentUser().getUsername()+"更新工作组信息了！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"更新工作组信息失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "toworkgroup";
	}

	public String toworkgroup(){
		return "toworkgroup";
	}
	
	public String detail() throws Exception{
		try {
			workgroup=workGroupService.getById(workgroup.getId());
            log.info(this.getCurrentUser().getUsername()+"打开查看工作组明细页面！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"打开查看工作组明细页面失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "detailUI";
	}
	
	public String addUI() throws Exception{
		List list;
		List list1;
		try {
			list=workGroupService.getAllWorkGroup();
			list1=workGroupService.getNoWorkFlowGroup();
			for(int i=0;i<list.size();i++){
				allworkgroup+=(String)list.get(i)+",";
			}
			if(allworkgroup!=null && !("").equals(allworkgroup)){
				allworkgroup=allworkgroup.substring(0,allworkgroup.length()-1);
			}
			for(int i=0;i<list1.size();i++){
				allnoworkflowgroup+=(String)list1.get(i)+",";
			}
			if(allnoworkflowgroup!=null && !("").equals(allnoworkflowgroup)){
				allnoworkflowgroup=allnoworkflowgroup.substring(0,allnoworkflowgroup.length()-1);
			}
			log.info(this.getCurrentUser().getUsername()+"打开添加工作组页面！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"打开添加工作组页面失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addUI";
	}
	
	public void add() throws Exception{
		try {
			workgroup.setStatus(StatusUtil.CONST_WORKGROUP_STATUS_ADD); // 工作组为新增状态
			workgroup.setUsername(currentUser.getUsername());
			// 解决非工作溢出组有", "值入库
			String str=workgroup.getNoworkoverflowGroup();
			if(str.indexOf(",")!=-1){
				workgroup.setNoworkoverflowGroup(str.substring(0,str.indexOf(",")));
			}
			workGroupService.save(workgroup);
			log.info(this.getCurrentUser().getUsername()+"添加工作组！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"添加工作组失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete() throws Exception{
		try {
			workgroup=workGroupService.getById(workgroup.getId());
			if(workgroup.getStatus().intValue()==StatusUtil.CONST_WORKGROUP_STATUS_ADD.intValue()){  // 若为已添加的状态
				workGroupService.delete(workgroup.getId());// 直接库中删除记录
			}else {  // 若不是新增状态，该改为什么状态就改为什么状态
				workgroup.setStatus(StatusUtil.CONST_WORKGROUP_STATUS_DELETE);  // 工作组变为删除状态
				workgroup.setUsername(currentUser.getUsername());
				// 更新为删除状态，但库中并不删除记录
				workGroupService.update(workgroup);
			}
			log.info(this.getCurrentUser().getUsername()+"删除工作组！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"删除工作组失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void isrepeatName() throws Exception{
		try {
			Long wgid=-1l;
			if(request.getParameter("workgroupid")!=null)
				wgid=Long.parseLong(request.getParameter("workgroupid"));
			int flag=0;
			flag=workGroupService.checkWGExist(workgroupname, wgid);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(this.getCurrentUser().getUsername()+"验证工作组名称重复！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"验证工作组名称重复失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void sendMail() throws Exception{
		try {
			List<TService400Workgroup> workgrouplist = workGroupService.getWorkGroupByUsername(currentUser.getUsername());
			int flag=0;
			if(workgrouplist!=null && workgrouplist.size()!=0){
					File file=FileUtils.getDistFileByType(1);
					String titlepath=Conf.getValue("title.file");
					// 获得文件的相对路径
					String filepath=file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(titlepath)+titlepath.length()+1, file.getAbsolutePath().length());
					System.out.println(filepath);
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					String date=format.format(new Date());
					TService400File file400=new TService400File();
					file400.setSendperson(currentUser.getUsername());
					file400.setSendtime(date);
					file400.setFilepath(filepath);
					file400.setStatus(StatusUtil.CONST_FILE_STATUS_NOBIHUAN); //非闭环
					file400.setType(TService400File.STATUS_TYPE_GROUP);
					fileService.save(file400);
					//TODO username,file400.getId()
					MailUtil.sendWorkGroupMail(workgrouplist, currentUser,file);
//					HashMap<String,Object> map = new HashMap<String,Object>();
//					map.put("username", currentUser.getUsername());
//					map.put("id", file400.getId().longValue());
//					if(!MailService.sendMail(MailService.MAIL_TYPE_GROUP,map)){
//						log.info("邮件发送失败。");
//					}
					// 邮件发送后status=6 的删除状态的工作组要变为status=11
					List<TService400Workgroup> deleteList=new ArrayList<TService400Workgroup>();
					for(int i=0;i<workgrouplist.size();i++){
						TService400Workgroup group=workgrouplist.get(i);
						if(group.getStatus().intValue()==StatusUtil.CONST_WORKGROUP_STATUS_DELETE.intValue())
							deleteList.add(group);
					}
					String delete_ids="";
					for(TService400Workgroup wg:deleteList){
						delete_ids+=wg.getId()+",";
					}
					if(!"".equals(delete_ids)){
						delete_ids="("+delete_ids.substring(0,delete_ids.length()-1)+")";
						workGroupService.updateStatusByDeleteIds(delete_ids,date);
					}
					
					// 邮件发送其他变为已发送邮件状态
					String ids="";
					for(TService400Workgroup wg:workgrouplist){
						if(wg.getStatus().intValue()!=StatusUtil.CONST_WORKGROUP_STATUS_DELETE.intValue())
						   ids+=wg.getId()+",";
					}
					if(!"".equals(ids)){
						ids="("+ids.substring(0,ids.length()-1)+")";
						workGroupService.updateStatusByIds(ids,date);
					}
					flag=1;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(this.getCurrentUser().getUsername()+"工作组维护申请发送邮件！");
		} catch (IOException e) {
			log.error(this.getCurrentUser().getUsername()+"工作组维护申请发送邮件失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String toList(){
	   return "toworkgroup";
	}
	
	public TService400Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(TService400Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public String getWorkOverFlow() {
		return workOverFlow;
	}

	public void setWorkOverFlow(String workOverFlow) {
		this.workOverFlow = workOverFlow;
	}

	public String getNoworkOverFlow() {
		return noworkOverFlow;
	}

	public void setNoworkOverFlow(String noworkOverFlow) {
		this.noworkOverFlow = noworkOverFlow;
	}

	public String getAllworkgroup() {
		return allworkgroup;
	}

	public void setAllworkgroup(String allworkgroup) {
		this.allworkgroup = allworkgroup;
	}

	public String getWorkgroupname() {
		return workgroupname;
	}

	public void setWorkgroupname(String workgroupname) {
		this.workgroupname = workgroupname;
	}

	public Long getWorkgroupid() {
		return workgroupid;
	}

	public void setWorkgroupid(Long workgroupid) {
		this.workgroupid = workgroupid;
	}

	public String getAllnoworkflowgroup() {
		return allnoworkflowgroup;
	}

	public void setAllnoworkflowgroup(String allnoworkflowgroup) {
		this.allnoworkflowgroup = allnoworkflowgroup;
	}

}
