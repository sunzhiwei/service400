package com.yixin.service400.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Role;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.bean.TService400User400num;
import com.yixin.service400.bean.TService400UserRole;
import com.yixin.service400.util.PageUtil;
import com.yixin.service400.util.Pagination;

@Controller
@Scope("prototype")
public class User400Action extends ModelDrivenBaseAction<TService400User> {
	static Logger log = Logger.getLogger(User400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	
	private TService400User ts = new TService400User();
	private String roleidlist;
	private String service400Idlist="";
	private List<TService400Role> rolelist;
	private List<Long> userroleidList;//当前用户的角色集合
	private List<TService400Applicationform> appformlist;
	private List<Long> user400numserviceidList;
	private String checkusername="";
    private Long checkuserid=-1l;
	public String addUserUI() throws Exception{
		try {
			rolelist=user400Service.selectRoleList(); // 所有角色集合
			appformlist=user400Service.selectApplicationformList();
			log.info(currentUser.getUsername() + "打开添加用户页面!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "打开添加用户页面失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addUser";
	}
	public void addUser() throws Exception{
		try {
			String roleidlist1 = roleidlist.replaceAll(" ", "");
			String service400Idlist1=service400Idlist.replaceAll(" ", "");
			ts.setPassword(DigestUtils.md5Hex(ts.getPassword())); // 要使用MD5摘要
			user400Service.save(ts);
			String[] rolechoseidlist = roleidlist1.split(",");
			String[] service400Idchoseidlist = service400Idlist1.split(",");
			for(int i=0;i<rolechoseidlist.length;i++){
				TService400UserRole userRole=new TService400UserRole();
				userRole.setUserid(ts.getId());
				userRole.setRoleid(Long.parseLong(rolechoseidlist[i]));
				userRoleService.save(userRole);
			}
			for(int i=0;i<service400Idchoseidlist.length;i++){
				TService400User400num user400num=new TService400User400num();
				user400num.setUserid(ts.getId());
				if (service400Idchoseidlist[i] != null&& !"".equals(service400Idchoseidlist[i]))
					user400num.setService400num(Long.parseLong(service400Idchoseidlist[i]));
				user400numService.save(user400num);
			}
			log.info(currentUser.getUsername() + "添加用户!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "添加用户失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void update() throws Exception{
		try {
			user400Service.update(ts);
			userRoleService.deleteByUserid(ts.getId());
			user400numService.deleteByUserid(ts.getId());
			
			String roleidlist1 = roleidlist.replaceAll(" ", "");
			String service400Idlist1=service400Idlist.replaceAll(" ", "");
			String[] rolechoseidlist = roleidlist1.split(",");
			String[] service400Idchoseidlist = service400Idlist1.split(",");
			for(int i=0;i<rolechoseidlist.length;i++){
				TService400UserRole userRole=new TService400UserRole();
				userRole.setUserid(ts.getId());
				userRole.setRoleid(Long.parseLong(rolechoseidlist[i]));
				userRoleService.save(userRole);
			}
			for(int i=0;i<service400Idchoseidlist.length;i++){
				TService400User400num user400num=new TService400User400num();
				user400num.setUserid(ts.getId());
				if (service400Idchoseidlist[i] != null&& !"".equals(service400Idchoseidlist[i]))
				         user400num.setService400num(Long.parseLong(service400Idchoseidlist[i]));
				user400numService.save(user400num);
			}
			log.info(currentUser.getUsername() + "更新用户!");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "更新用户失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String listUser(){
		return "listUser";
	}
	
	@SuppressWarnings("unchecked")
	public String queryUserList() throws Exception{
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		try {
			map = setMapValue(map);
			int totalCounts = user400Service.getTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage, pageSize, totalCounts);
			// 查询数据集
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(map2);
			List list = user400Service.queryPageByParams(map2);
			page.setRows(list);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername() + "查看用户列表!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername() + "查看用户列表失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}
	
	@SuppressWarnings("unchecked")
	private Map setMapValue(Map map) throws Exception{
		map.put("username", request.getParameter("username"));
		return map;
	}
	
	/**
	 * 描述：判断用户是否已经存在
	 */
	public void checkUserExist() throws Exception{
		try {
			int flag=0;
			flag=user400Service.checkUserExist(checkusername,checkuserid);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(this.getCurrentUser().getUsername() + "验证用户是否存在!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername() + "验证用户是否存在失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void checkPassword() throws Exception{
		try {
			int flag=0;
			flag=user400Service.checkPassword(currentUser, request.getParameter("oldpassword"));
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(this.getCurrentUser().getUsername() + "验证当前用户密码是否填写正确!");
		} catch (IOException e) {
			log.error(this.getCurrentUser().getUsername() + "验证当前用户密码是否填写正确失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delete() throws Exception{
		try {
			user400Service.delete(ts.getId());
			userRoleService.deleteByUserid(ts.getId());// 删除用户的权限
			user400numService.deleteByUserid(ts.getId()); // 删除400号码记录所绑定的用户记录
			log.info(this.getCurrentUser().getUsername() + "删除用户!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername() + "删除用户失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String edit() throws Exception{
		try {
			ts=user400Service.getById(ts.getId());
			rolelist=user400Service.selectRoleList(); // 所有角色集合
			userroleidList=userRoleService.userroleidlist(ts.getId()); // 当前用户角色
			appformlist=user400Service.selectApplicationformList(); // 所有申请表中的400号码
			user400numserviceidList=user400Service.user400numserviceidByUserid(ts.getId()); // 当前用户绑定的400号码
			log.info(this.getCurrentUser().getUsername() + "打开编辑用户页面!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername() + "打开编辑用户页面失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "editUI";
	}
	
	public String updatePWDUI(){
		return "updatePWDUI";
	}
	
	public void updatePWD() throws Exception{
		try {
			ts.setPassword(DigestUtils.md5Hex(ts.getPassword())); // 要使用MD5摘要
			user400Service.update(ts);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	/*******************************************/
	public TService400User getTs() {
		return ts;
	}

	public void setTs(TService400User ts) {
		this.ts = ts;
	}

	public String getRoleidlist() {
		return roleidlist;
	}

	public void setRoleidlist(String roleidlist) {
		this.roleidlist = roleidlist;
	}

	public List<TService400Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<TService400Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<TService400Applicationform> getAppformlist() {
		return appformlist;
	}

	public void setAppformlist(List<TService400Applicationform> appformlist) {
		this.appformlist = appformlist;
	}

	public String getCheckusername() {
		return checkusername;
	}

	public void setCheckusername(String checkusername) {
		this.checkusername = checkusername;
	}

	public String getService400Idlist() {
		return service400Idlist;
	}

	public void setService400Idlist(String service400Idlist) {
		this.service400Idlist = service400Idlist;
	}
	public List<Long> getUserroleidList() {
		return userroleidList;
	}
	public void setUserroleidList(List<Long> userroleidList) {
		this.userroleidList = userroleidList;
	}
	public List<Long> getUser400numserviceidList() {
		return user400numserviceidList;
	}
	public void setUser400numserviceidList(List<Long> user400numserviceidList) {
		this.user400numserviceidList = user400numserviceidList;
	}
	public Long getCheckuserid() {
		return checkuserid;
	}
	public void setCheckuserid(Long checkuserid) {
		this.checkuserid = checkuserid;
	}
}
