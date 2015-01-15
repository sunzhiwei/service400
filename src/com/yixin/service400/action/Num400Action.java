package com.yixin.service400.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.util.PageUtil;
import com.yixin.service400.util.Pagination;

@Controller
@Scope("prototype")
public class Num400Action extends ModelDrivenBaseAction<TService400User> {
	static Logger log = Logger.getLogger(Num400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	private String path;

	List<TService400> list;

	private TService400 ts = new TService400();
	
	public String to400page() {
			return "to400page";
	}

	public String add400() throws Exception{
		try {
			ts.setStatus(TService400.WSY);
			num400Service.addTService400(ts);
			log.info(this.getCurrentUser().getUsername()+"添加400号码!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"添加400号码失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "add400";
	}
	
	public void add400ajax() throws Exception{
		try {
			ts.setPhonenum(request.getParameter("phonenum"));
			ts.setPrice(Double.parseDouble(request.getParameter("price")));
			ts.setOperationCompany(request.getParameter("operationCompany"));
			ts.setStatus(TService400.WSY);
			num400Service.addTService400(ts);
			log.info(this.getCurrentUser().getUsername()+"添加400号码!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"添加400号码失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String to400List(){
		return "to400List";
	}

	public void update400page() throws Exception{
		try {
			String id = request.getParameter("id");
			ts = num400Service.getTService400(Long.parseLong(id));
			PrintWriter out = response.getWriter();
			out.print(ts.getPhonenum() + ":" + ts.getPrice());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String update400() throws Exception{
		try {
			num400Service.update400(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update400";
	}

	public String delete400() throws Exception{
		try {
			String id = request.getParameter("id");
			num400Service.delete400(Long.parseLong(id));
			log.info(this.getCurrentUser().getUsername()+"删除400号码!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"删除400号码失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "delete400";
	}

	@SuppressWarnings("unchecked")
	public String queryPageJson() throws Exception{
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		try {
			map=setMapValue(map);
			int totalCounts = num400Service.getTotalCountsByParams(map);
			Pagination page = new Pagination(currentPage,pageSize,totalCounts);
			//查询数据集
			Map map2 = PageUtil.getMap(page);
			map2=setMapValue(map2);
			List list = num400Service.queryPageByParams(map2);
			List service400List=new ArrayList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				TService400 service400=(TService400) it.next();
				Map service400Map=new HashMap();
				service400Map.put("id", service400.getId());
				service400Map.put("phonenum", service400.getPhonenum());
				service400Map.put("price", service400.getPrice());
				service400Map.put("operation_company", service400.getOCString());
				service400Map.put("status", service400.getStatus());
				service400List.add(service400Map);
			}
			page.setRows(service400List);
			this.pagination = page;
			log.info(this.getCurrentUser().getUsername()+"查看400号码列表！");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"查看400号码列表失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return "queryPageJson";
	}
	
	@SuppressWarnings("unchecked")
	private Map setMapValue(Map map) throws Exception{
		map.put("phonenum", request.getParameter("phonenum"));
		map.put("company", request.getParameter("company"));
		return map;
	}
	
	public void isrepeatnum() throws Exception{
		try {
			int flag=num400Service.getRepeatCounts(request.getParameter("phonenum"));
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
			log.info(this.getCurrentUser().getUsername()+"验证400号码重复!");
		} catch (Exception e) {
			log.error(this.getCurrentUser().getUsername()+"验证400号码重复失败!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/***********************************************************/

	public TService400 getTs() {
		return ts;
	}

	public void setTs(TService400 ts) {
		this.ts = ts;
	}

	public List<TService400> getList() {
		return list;
	}

	public void setList(List<TService400> list) {
		this.list = list;
	}

	public String getPath() {
		if (path == null || "".equals(path)) {
			path = request.getContextPath();
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
