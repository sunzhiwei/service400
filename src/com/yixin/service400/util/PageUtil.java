package com.yixin.service400.util;

import java.util.HashMap;
import java.util.Map;


public class PageUtil {
	/**
	 * 
	 * 描述：字符串转换成整数:页数的转换
	 * 2012-12-30 下午02:28:56 by ygq
	 * @version
	 * @param str
	 * @return
	 */
	public  static int strToPage(String str){
		if(null == str || "".equals(str)){
			return 1;//默认是第一页
		}else{
			return Integer.parseInt(str);
		}
	}
	public  static int strToPageSize(String str){
		if(null == str || "".equals(str)){
			return 5;//默认是第一页
		}else{
			return Integer.parseInt(str);
		}
	}
	/**
	 * 
	 * 描述：这里存的是开始行和结束行的值
	 * 2012-12-29 下午05:08:57 by ygq
	 * @version
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getMap(PageBean page){
		int curPage = page.getCurPage();
		int start = (curPage-1)*page.getPageSize();
		int end = curPage*page.getPageSize();
		Map map = new HashMap();
		map.put("startRow", start);
		map.put("endRow", end);
		return map;
	}
	/**
	 * 新的分页的方法
	 * 描述：这里存的是开始行和结束行的值
	 * 2012-12-29 下午05:08:57 by ygq
	 * @version
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getMap(Pagination page){
		int curPage = page.getCurrentPage();
		int start = (curPage-1)*page.getPageSize()+1;
		int end = curPage*page.getPageSize();
		Map map = new HashMap();
		map.put("startRow", start);
		map.put("endRow", end);
		return map;
	}
}
