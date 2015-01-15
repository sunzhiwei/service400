package com.yixin.service400.base;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.service.Apply400Service;
import com.yixin.service400.service.FileService;
import com.yixin.service400.service.IVR400Service;
import com.yixin.service400.service.KeyGroup400Service;
import com.yixin.service400.service.LoginService;
import com.yixin.service400.service.Notes400Service;
import com.yixin.service400.service.Num400Service;
import com.yixin.service400.service.PrivilegeService;
import com.yixin.service400.service.Query400Service;
import com.yixin.service400.service.User400Service;
import com.yixin.service400.service.User400numService;
import com.yixin.service400.service.UserRoleService;
import com.yixin.service400.service.UserService;
import com.yixin.service400.service.WorkGroupService;
import com.yixin.service400.util.PageBean;
import com.yixin.service400.util.Pagination;

public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Pagination pagination = new Pagination();
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	public TService400User currentUser=(TService400User) ActionContext.getContext().getSession().get("user");
	
	@Resource
	protected UserService userService;
	@Resource
	protected LoginService loginService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected WorkGroupService workGroupService;
	@Resource
	protected UserRoleService userRoleService;
	@Resource
	protected User400numService user400numService;
	@Resource
	protected FileService fileService;
	@Resource
	protected Apply400Service apply400Service;
	@Resource
	protected Num400Service num400Service;
	@Resource
	protected Notes400Service notes400Service;
	@Resource
	protected IVR400Service ivr400Service;
	@Resource
	protected WorkGroupService workGroup400Service;
	@Resource
	protected KeyGroup400Service keyGroup400Service;
	@Resource
	protected User400Service user400Service;
	@Resource
	protected Query400Service query400Service;
	

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	protected PageBean pageBean = new PageBean();
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	protected int querytype;//查询类型
	protected String condition;//查询条件
	protected String message;
	/**
	 * 返回json格式的字符串
	 */
	protected String json;
	public int getQuerytype() {
		return querytype;
	}
	public void setQuerytype(int querytype) {
		this.querytype = querytype;
	}
	public String getCondition() {
		return condition==null?"":condition.trim();
	}
	public void setCondition(String condition) {
		this.condition=(condition==null?"":condition.trim());
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	public TService400User getCurrentUser() {
		return currentUser;
	}
	
	public void settCurrentUser(TService400User currentUser) {
		this.currentUser=currentUser;
	}

	// 页码默认为第1页
	protected int pageNum = 1;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 
	 * 描述：将json格式的字符串写到客户端
	 * 2012-12-29 下午03:15:41 by ygq
	 * @version
	 * @param json
	 * @throws Exception
	 */
	public void writerJsonToClient(String json) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	/**
	 * 
	 * 描述：将json格式的字符串写到客户端
	 * 增加了	request.setCharacterEncoding("utf-8");
	 * 2012-12-29 下午03:15:41 by ygq
	 * @version
	 * @param json
	 * @throws Exception
	 */
	public void writerJsonToClientEncode(String json) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	/**
	 * 保存上传的文件，并返回文件在服务端的真实存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		// >> 获取路径
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());
		// >> 如果文件夹不存在，就创建
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs(); // 递归的创建不存在的文件夹
		}
		// >> 拼接路径
		String path = basePath + subPath + UUID.randomUUID().toString();
		// >> 移动文件
		upload.renameTo(new File(path)); // 如果目标文件夹不存在，或是目标文件已存在，就会不成功，返回false，但不报错。
		return path;
	}

}
