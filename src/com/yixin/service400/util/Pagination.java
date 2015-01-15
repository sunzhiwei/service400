package com.yixin.service400.util;

import java.util.List;

/**
 * 现在 我们建立一个 分页类，这个类具有一些 属性，这些属性其中，
 * currentPage pageSize  是用户发送的数据，其他都是我们通过和数据库交互产生的
 * @author Administrator
 *
 */
public class Pagination {
	/**
	 * 用户发送的数据
	 */
	private int currentPage;   // 当前页
	private int pageSize;      // 每页要求最多显示的记录
	/**
	 * 数据库查询得到
	 */
	private int total;       // 查询 数据库 得到的所有 记录总数
	@SuppressWarnings("unchecked")
	private List rows;       //  查询 得到 本页的数据，返回一个 装有pod对象的 List
	/**
	 * 经过计算得出的数据 
	 */
	private int pageCount;  // 总页数
	private int startPageIndex;  // 页码列表开始的索引
	private int endPageIndex;    // 页面列表 结束的 索引 
	
	
	/**
	 * 无参构造方法 
	 */
	public Pagination() {
	}
	/**
	 * 
	 * 构造函数：不传List的方法
	 * @param currentPage
	 * @param pageSize
	 * @param total
	 */
	public Pagination(int currentPage, int pageSize, int total) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		pageCount = (total+pageSize-1)/pageSize;    //总页数
		if(pageCount <= pageSize) {	// 总页码不大于NicConfiguration.getPageSize()
			startPageIndex = 1 ;		// 页码列表的开始索引
			endPageIndex = pageCount ;	// 页码列表的结束索引
		} else {	// 总页码大于NicConfiguration.getPageSize() 
			startPageIndex = currentPage - 4 ;
			endPageIndex = currentPage + 5 ;

			if(startPageIndex < 1 ){	// 前面的页码不足4个   
				startPageIndex = 1 ; 
				endPageIndex = pageSize ;
			} else if(endPageIndex > pageCount) {	// 后面的页码不足5个 
				startPageIndex =  endPageIndex - pageSize + 1; 
				endPageIndex = pageCount ;				
			}
			
		}
	}
	/**
	 * 有参构造方法
	 */

	@SuppressWarnings("unchecked")
	public Pagination(int currentPage, int pageSize, int total, List rows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
		pageCount = (total+pageSize-1)/pageSize;    //总页数
		if(pageCount <= pageSize) {	// 总页码不大于NicConfiguration.getPageSize()
			startPageIndex = 1 ;		// 页码列表的开始索引
			endPageIndex = pageCount ;	// 页码列表的结束索引
		} else {	// 总页码大于NicConfiguration.getPageSize() 
			startPageIndex = currentPage - 4 ;
			endPageIndex = currentPage + 5 ;

			if(startPageIndex < 1 ){	// 前面的页码不足4个   
				startPageIndex = 1 ; 
				endPageIndex = pageSize ;
			} else if(endPageIndex > pageCount) {	// 后面的页码不足5个 
				startPageIndex =  endPageIndex - pageSize + 1; 
				endPageIndex = pageCount ;				
			}
			
		}
		
	}
	
	
	
	/**
	 * geters and setters
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@SuppressWarnings("unchecked")
	public List getRows() {
		return rows;
	}
	@SuppressWarnings("unchecked")
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartePageIndex() {
		return startPageIndex;
	}
	public void setStartePageIndex(int startePageIndex) {
		this.startPageIndex = startePageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}