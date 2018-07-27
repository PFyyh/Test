package com.util.page;

import java.util.List;

public class PageBean<T> {
	private int totalRows;				//������
	private int totalPages; 			//��ҳ��
	private int pageIndex = 1;  		//��ǰҳ
	private int pageSize = 10;   		//ÿҳ������
	private List<T> pageData = null;	//��ѯ������Ϣ����
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
