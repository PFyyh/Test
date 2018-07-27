package com.util.page;

import java.util.List;

import net.sf.json.JSONObject;
public class PageMethods<T> {
	PageBean<T> page = null;
	
	public PageMethods() {
	}
	/**
	 * 只传入总行数，默认每页行数为10,默认起始页为第一页。
	 * @param totalRows
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows) throws IndexOutOfPageException{
		this(totalRows,10,1);
	}
	/**
	 * 传入总行数，每页行数自定义，默认起始页为第一页。
	 * @param totalRows
	 * @param PageSize
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows,int pageSize) throws IndexOutOfPageException{
		this(totalRows, pageSize, 1);
	}
	/**
	 * 自定义总行数，自定义每页行数，自定义起始页。
	 * 需要对数据进行检查，是否合法进行检查。
	 * @param totalRows
	 * @param PageSize
	 * @param pageIndex
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows,int pageSize,int pageIndex) throws IndexOutOfPageException{
		page = new PageBean<T>();  			//创建对象
		this.setTotalRows(totalRows);		//设置总行数
		this.setPageSize(pageSize);			//设置每页行数
		this.setTotalPages();		//设置总页数
		this.setPageIndex(pageIndex);		//设置当前页面
	}
	/**
	 * 求总页数。
	 */
	private void setTotalPages() {
		int totalRows = page.getTotalRows();
		int pageSize = page.getPageSize();
		int totalPage = totalRows/pageSize;
		if(totalPage==0){
			totalPage = 1;
		}else if( (totalRows/pageSize)!=0){
			totalPage++;
		}
		page.setTotalPages(totalPage);
		
	}
	/**
	 * 检查总行数是否合法
	 * @param totalRows
	 * @throws IndexOutOfPageException
	 */
	private void setTotalRows(int totalRows) throws IndexOutOfPageException {
		if (totalRows<0) {
			throw new IndexOutOfPageException("记录数异常");
		}
		page.setTotalRows(totalRows);
	}
	/**
	 * 检查每页行数是否异常
	 * @param pageSize
	 * @throws IndexOutOfPageException
	 */
	private void setPageSize(int pageSize) throws IndexOutOfPageException {
		if (pageSize<=0) {
			throw new IndexOutOfPageException("每页行数异常");
		}
		page.setPageSize(pageSize);
	}
	/**
	 * 获取每页条数
	 * @return
	 */
	public int getPageSize(){
		return page.getPageSize();
	}
	/**
	 * 设置当前页面,如果输入的当前页面是会抛出异常的。
	 * @param PageIndex
	 * @throws IndexOutOfPageException 
	 */
	public void setPageIndex(int pageIndex) throws IndexOutOfPageException{
		System.out.println("pageIndex:"+pageIndex+" pageSize:"+page.getPageSize());
		//判断输入是否超出范围
		if(pageIndex < 0||pageIndex > page.getTotalPages()){
			throw new IndexOutOfPageException("页数为"+pageIndex+"总页数"+page.getTotalPages()+"页数异常");
		}else{
			page.setPageIndex(pageIndex);
		}
	}
	/**
	 * 获取当前页
	 * @return
	 */
	public int getPageIndex(){
		return page.getPageIndex();
	}

	/**
	 * 获取当前页内容
	 * @return
	 */
	public List<T> getPageData(){
		return page.getPageData();
	}
	/**
	 * 设置当前页面内容
	 * @param pageData
	 */
	public void setPageData(List<T> pageData){
		page.setPageData(pageData);
	}
	public JSONObject toJSON(int code,String msg){
		JSONObject userJSON = new JSONObject();
		userJSON.put("code", code);
		userJSON.put("count",page.getTotalRows());
		userJSON.put("msg", "");
		userJSON.put("data", page.getPageData());
		return userJSON;
	}
}
