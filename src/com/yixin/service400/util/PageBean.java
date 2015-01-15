package com.yixin.service400.util;
import java.util.Collection;
public class PageBean {
	@SuppressWarnings("unchecked")
	protected Collection result;
	private int pageSize = 5;
	private int firstPage=1;
	private int curPage=1;
	private int nextPage;
	private int rowCount;
	private int maxPage = 0;//最大页数
	public PageBean(){
		curPage = 1;
	}
	public PageBean(int pageSize){
		curPage = 1;
		this.pageSize = pageSize;
	}
	public PageBean(int pageSize,int rowCount){
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.maxPage = (int)Math.ceil(rowCount*1.0/pageSize);//总共有多少页
	}
	public PageBean(int pageSize,int curPage,int rowCount){
		this.pageSize = pageSize;
		this.curPage = curPage;
		this.rowCount = rowCount;
		this.maxPage = (int)Math.ceil(rowCount*1.0/pageSize);//总共有多少页
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	@SuppressWarnings("unchecked")
	public Collection getResult() {
		return result;
	}
	@SuppressWarnings("unchecked")
	public void setResult(Collection result) {
		this.result = result;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
