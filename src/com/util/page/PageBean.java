package com.util.page;

import java.util.List;

public class PageBean<T> {
	private int totalRows;				//总条数
	private int totalPages; 			//总页数
	private int pageIndex = 1;  		//当前页
	private int pageSize = 10;   		//每页多少条
	private List<T> pageData = null;	//查询到的信息内容
 	public List<T> getPageData() {
 		System.out.println("PageBean.getPageData():::::::::::");
 		for(T tmp:pageData){
 			System.out.println(tmp);
 		}
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
}
