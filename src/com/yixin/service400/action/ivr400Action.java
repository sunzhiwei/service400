package com.yixin.service400.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400File;
import com.yixin.service400.bean.TService400Key;
import com.yixin.service400.bean.TService400KeyWorkgroup;
import com.yixin.service400.bean.TService400Workgroup;
import com.yixin.service400.util.Conf;
import com.yixin.service400.util.FileUtils;
import com.yixin.service400.util.MailService;
import com.yixin.service400.util.MailUtil;
import com.yixin.service400.util.StatusUtil;

@Controller
@Scope("prototype")
public class ivr400Action extends ModelDrivenBaseAction<TService400Key> {
	static Logger log = Logger.getLogger(ivr400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	private String path;
	private List<TService400Workgroup> wgList = new ArrayList<TService400Workgroup>();

	private Long groupid;
	private String phonenum;

	public void sendIVRMailajax() throws Exception{
		try {
			int flag=0;  // 成功标志
			phonenum=request.getParameter("phonenum");
//			List<TService400Key> Ivrlist=ivr400Service.getTService400Key(currentUser);
			List<TService400Key> Ivrlist=ivr400Service.getTService400KeyByCondition(currentUser, phonenum);
			List<TService400Applicationform> appForm=ivr400Service.getApplicationformByNum(phonenum);
//			List<TService400KeyWorkgroup> ivr_workgrouplist=ivr400Service.getTService400KeyWorkgroup(currentUser);
			List<TService400KeyWorkgroup> ivr_workgrouplist=ivr400Service.getTService400KeyWorkgroupByCon(currentUser,phonenum);
			if(ivr_workgrouplist!=null){
				for(TService400KeyWorkgroup kwg:ivr_workgrouplist){
					TService400Key k=new TService400Key();
					TService400Workgroup wg=workGroup400Service.getById(kwg.getWorkgroupId());
					k.setName(wg.getName());
					k.setContent(wg.getName());
					k.setPath(kwg.getPath());
					k.setStatus(kwg.getIvr_workgroup_status());
					k.setWorkgroup_flag(1); //工作组标志
					Ivrlist.add(k);
				}
			}
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String date=format.format(new Date());
			if(Ivrlist!=null && Ivrlist.size()!=0){
				File file=FileUtils.getDistFileByType(2);
				flag=1;  // 邮件发送成功
				String titlepath=Conf.getValue("title.file");
				// 获得文件的相对路径
				String filepath=file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(titlepath)+titlepath.length()+1, file.getAbsolutePath().length());
				TService400File file400=new TService400File();
				file400.setSendperson(currentUser.getUsername());
				file400.setSendtime(date);
				file400.setFilepath(filepath);
				file400.setStatus(StatusUtil.CONST_FILE_STATUS_NOBIHUAN); //非闭环
				file400.setType(TService400File.STATUS_TYPE_IVR);
				fileService.save(file400);
				//TODO username,phonenum,file400.getId()
				MailUtil.sendIVRMail(Ivrlist, currentUser,file,appForm);
//				HashMap<String,Object> map = new HashMap<String,Object>();
//				map.put("username", currentUser.getUsername());
//				map.put("phonenum", phonenum);
//				map.put("id", file400.getId().longValue());
//				if(!MailService.sendMail(MailService.MAIL_TYPE_KEY,map)){
//					log.info("邮件发送失败。");
//				}
				// 邮件发送后status=6 的删除状态的IVR变更为status=11
				List<TService400Key> deleteList=new ArrayList<TService400Key>();
				for(int i=0;i<Ivrlist.size();i++){
					TService400Key key=Ivrlist.get(i);
					if(key.getStatus().intValue()==TService400Key.STATUS_DELETE.intValue())
						deleteList.add(key);
				}
				String delete_ids="";
				for(TService400Key key:deleteList){
					delete_ids+=key.getId()+",";
				}
				if(!"".equals(delete_ids)){
					delete_ids="("+delete_ids.substring(0,delete_ids.length()-1)+")";
					ivr400Service.updateStatusByDeleteIds(delete_ids, date);
				}
			
				// 邮件发送后其他变为已发送邮件状态
				String ids="";
				for(TService400Key key:Ivrlist){
					if(key.getStatus().intValue()!=TService400Key.STATUS_DELETE.intValue())
					   ids+=key.getId()+",";
				}
				if(!"".equals(ids)){
					ids="("+ids.substring(0,ids.length()-1)+")";
					ivr400Service.updateStatusByIds(ids,date);
				}
			}
			if(ivr_workgrouplist!=null && ivr_workgrouplist.size()!=0){
				// 邮件发送后status=6 的删除状态的key_workgroup变更为status=11
				List<TService400KeyWorkgroup> delkeygroupList=new ArrayList<TService400KeyWorkgroup>();
				for(int i=0;i<ivr_workgrouplist.size();i++){
					TService400KeyWorkgroup keygroup=ivr_workgrouplist.get(i);
					if(keygroup.getIvr_workgroup_status().intValue()==TService400Key.STATUS_DELETE.intValue())
						delkeygroupList.add(keygroup);
				}
				String delete_ids="";
				for(TService400KeyWorkgroup kg:delkeygroupList){
					delete_ids+=kg.getKeyWorkgroupId()+",";
				}
				if(!"".equals(delete_ids)){
					delete_ids="("+delete_ids.substring(0,delete_ids.length()-1)+")";
					keyGroup400Service.updateStatusByDeleteIds(delete_ids, date);
				}
				
				// 邮件发送后其他变为已发送邮件状态
				String ids="";
				for(TService400KeyWorkgroup key:ivr_workgrouplist){
					if(key.getIvr_workgroup_status().intValue()!=TService400Key.STATUS_DELETE.intValue())
					   ids+=key.getKeyWorkgroupId()+",";
				}
				if(!"".equals(ids)){
					ids="("+ids.substring(0,ids.length()-1)+")";
					keyGroup400Service.updateStatusByIds(ids,date);
				}
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(currentUser.getUsername() + "发送IVR维护申请邮件!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "发送IVR维护申请邮件失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public String deleteGroup() throws Exception{
		try {
			keyGroup400Service.deleteGroup(groupid, key.getId(), currentUser);
			log.info(currentUser.getUsername() + "解除IVR节点下的工作组!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "解除IVR节点下的工作组失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "deleteGroup";
	}

	public String addGroupKey() throws Exception{
		try {
			TService400Workgroup wg = workGroup400Service.getById(groupid);
			ivr400Service.addKeyGroup(key.getId(), wg,currentUser);
			log.info(currentUser.getUsername() + "绑定IVR节点下的工作组!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "绑定IVR节点下的工作组失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addGroupKey";
	}

	public String deleteKey() throws Exception{
		try {
			key.setUsername(currentUser.getUsername());
			ivr400Service.deleteTService400Key(key.getId());
			log.info(currentUser.getUsername() + "删除IVR节点!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "删除IVR节点失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "deleteKey";
	}

	public String updateKey() throws Exception{
		try {
			key.setUsername(currentUser.getUsername());
			ivr400Service.updateIVR400Service(key);
			log.info("修改:" + key.getContent() + "-->>" + key.getName());
			log.info(currentUser.getUsername() +"修改IVR节点!" );
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "修改IVR节点失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "updateKey";
	}

	public void keyNameSelf() throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			TService400Key tsk = ivr400Service.getTService400Key(key.getId());
			String names = ivr400Service.getNoHasName(tsk.getPid());
			log.info("得到没有用到的按键名：" + names);
			String[] sp = names.split(";");
			StringBuffer sb = new StringBuffer();
			for (String s : sp) {
				sb.append("<option value='" + s + "'>" + s + "</option>");
			}
			sb.append("<option selected='selected' value='" + tsk.getName()
					+ "'>" + tsk.getName() + "</option>");
			sb.append("-" + tsk.getContent());
			log.info("生成的备选项：" + sb.toString());
			out.write(sb.toString());
			log.info(currentUser.getUsername() + "获取自身IVR节点!");
		}catch (Exception e) {
			log.error(currentUser.getUsername() + "获取自身IVR节点失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void keyName() throws Exception{
		PrintWriter out;
		try {
			out = response.getWriter();
			String names = ivr400Service.getNoHasName(key.getId());
			log.info("得到没有用到的按键名：" + names);
			boolean b = ivr400Service.isHasGroup(key.getId());
			log.info("是否可以添加工作组：" + b);
			names = names + "-" + b;
			out.write(names);
			log.info(currentUser.getUsername() + "获取IVR名称!");
		}catch (Exception e) {
			log.error(currentUser.getUsername() + "获取IVR名称失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String addKey() throws Exception{
		try {
			System.out.println(request.getParameter("phonenum"));
			phonenum=request.getParameter("phonenum");
			List<TService400Applicationform> appForm=ivr400Service.getApplicationformByNum(phonenum);
			if("11".equals(key.getName())){
				TService400Applicationform tsa = apply400Service.getTService400ApplicationformById(key.getPid());
				key.setPath(tsa.getPhonenum().longValue()+"-11");
				key.setStatus(TService400Key.STATUS_ADD);
			}else{
				TService400Key tsk = ivr400Service.getTService400Key(key.getPid());
				key.setPath(tsk.getPath() + "-" + key.getName());
				key.setStatus(TService400Key.STATUS_ADD);
			}
			if (appForm != null && appForm.size() != 0)
				key.setPhonenum(appForm.get(0).getPhonenum() + "");
			key.setUsername(currentUser.getUsername());
			ivr400Service.addTService400Key(key);
			log.info(currentUser.getUsername() + "添加IVR节点!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "添加IVR节点失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addKey";
	}

	private List<TService400Applicationform> tsList = new ArrayList<TService400Applicationform>();

	public String applyList() throws Exception{
		try {
			int count=apply400Service.checkAdminByUser(currentUser);
			tsList = apply400Service.getHasApplyTService400ApplicationformList(currentUser,count);
			log.info("可维护的400号码列表：" + tsList);
			log.info(getPath());
			log.info(currentUser.getUsername() + "查询所要设置IVR的400号码列表!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "查询所要设置IVR的400号码列表失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "applyList";
	}
	boolean is11 = true;
	public String show() throws Exception{
		try {
			Map<String, Object> map = ActionContext.getContext().getSession();
			Long id = (Long) map.get("ts_id");
			String string = map.get("ts_phonenum").toString();
			keyList = ivr400Service.getTService400KeyListByPath(string);
			is11 = keyList.isEmpty();
			StringBuffer sb = new StringBuffer(tree);
			this.createTree(id, sb);
			tree = sb.toString();
			wgList = workGroup400Service.getWorkGroupList();
			log.info(currentUser.getUsername() + "进行IVR操作时，显示所有可用工作组!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "进行IVR操作时，显示所有可用工作组失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "show";
	}

	private TService400Key key = new TService400Key();
	private List<TService400Key> keyList = new ArrayList<TService400Key>();
	private String tree = "";

	public String showIVR() throws Exception{
		try {
			keyList = ivr400Service.getTService400KeyListByPath(key.getPath());
			Map<String, Object> map = ActionContext.getContext().getSession();
			map.put("ts_phonenum", key.getPath());
			map.put("ts_id", key.getPid());
			StringBuffer sb = new StringBuffer(tree);
			this.createTree(key.getPid(), sb);
			tree = sb.toString();
			is11 = keyList.isEmpty();
			log.info(tree);
			wgList = workGroup400Service.getWorkGroupList();
			log.info(currentUser.getUsername() + "显示400号码下IVR节点!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "显示400号码下IVR节点失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "showIVR";
	}

	private void createTree(Long pid, StringBuffer sb) throws Exception {
		List<TService400Key> tsk = getTSKeyByPid(pid);
		if (!tsk.isEmpty()) {
			sb.append("<ul>");
			for (TService400Key ts1 : tsk) {
				sb.append("<li>");
				sb.append("<a title=\"" + ts1.getContent()
						+ "\" href='javascript:clickNode(\"" + ts1.getId()
						+ "\")'>");
				if (ts1.getStatus().intValue() == TService400Key.STATUS_ADD.intValue()) {
					sb.append("<label style='color:red;'>");
					sb.append(("11").equals(ts1.getName()) ? "首问语" : "按键:"
							+ ts1.getName());
					sb.append("</label>");
				} else if (ts1.getStatus().intValue() == TService400Key.STATUS_UPDATE.intValue()) {
					sb.append("<label style='color:blue;'>");
					sb.append(("11").equals(ts1.getName()) ? "首问语" : "按键:"
							+ ts1.getName());
					sb.append("</label>");
				} else {
					sb.append(("11").equals(ts1.getName())  ? "首问语" : "按键:"
							+ ts1.getName());
				}
				sb.append("</a>");
				createTree(ts1.getId(), sb);
				sb.append("</li>");
			}
			sb.append("</ul>");
		} else {
			List<TService400Workgroup> list = ivr400Service
					.getWorkGroupByIVRid(pid);
			if (!list.isEmpty()) {
				sb.append("<ul>");
				for (TService400Workgroup wg : list) {
					sb.append("<li>");
					sb.append("<a href='javascript:clickGroup(\"" + wg.getId()
							+ "\",\"" + pid + "\")'>");
					if (wg.getIvrStatus().intValue() == TService400KeyWorkgroup.STATUS_ADD) {
						sb.append("<label style='color:red;'>");
						sb.append(wg.getName());
						sb.append("</label>");
					} else {
						sb.append(wg.getName());
					}
					sb.append("</a>");
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
		}
	}

	private List<TService400Key> getTSKeyByPid(Long id) throws Exception{
		List<TService400Key> list = new ArrayList<TService400Key>();
		for (TService400Key ts : keyList) {
			if (ts.getPid().longValue() == id.longValue()) {
				list.add(ts);
			}
		}
		return list;
	}
	
	/************************************************************************/
	public String getPath() {
		if (path == null || path.equals("")) {
			path = request.getContextPath();
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

	public TService400Key getKey() {
		return key;
	}

	public void setKey(TService400Key key) {
		this.key = key;
	}

	public List<TService400Key> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<TService400Key> keyList) {
		this.keyList = keyList;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public List<TService400Workgroup> getWgList() {
		return wgList;
	}

	public void setWgList(List<TService400Workgroup> wgList) {
		this.wgList = wgList;
	}

	public boolean isIs11() {
		return is11;
	}

	public void setIs11(boolean is11) {
		this.is11 = is11;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}
