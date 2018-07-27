package com.util.page;

import java.util.List;

import net.sf.json.JSONObject;
public class PageMethods<T> {
	PageBean<T> page = null;
	
	public PageMethods() {
	}
	/**
	 * ֻ������������Ĭ��ÿҳ����Ϊ10,Ĭ����ʼҳΪ��һҳ��
	 * @param totalRows
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows) throws IndexOutOfPageException{
		this(totalRows,10,1);
	}
	/**
	 * ������������ÿҳ�����Զ��壬Ĭ����ʼҳΪ��һҳ��
	 * @param totalRows
	 * @param PageSize
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows,int pageSize) throws IndexOutOfPageException{
		this(totalRows, pageSize, 1);
	}
	/**
	 * �Զ������������Զ���ÿҳ�������Զ�����ʼҳ��
	 * ��Ҫ�����ݽ��м�飬�Ƿ�Ϸ����м�顣
	 * @param totalRows
	 * @param PageSize
	 * @param pageIndex
	 * @throws IndexOutOfPageException 
	 */
	public PageMethods(int totalRows,int pageSize,int pageIndex) throws IndexOutOfPageException{
		page = new PageBean<T>();  			//��������
		this.setTotalRows(totalRows);		//����������
		this.setPageSize(pageSize);			//����ÿҳ����
		this.setTotalPages();		//������ҳ��
		this.setPageIndex(pageIndex);		//���õ�ǰҳ��
	}
	/**
	 * ����ҳ����
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
	 * ����������Ƿ�Ϸ�
	 * @param totalRows
	 * @throws IndexOutOfPageException
	 */
	private void setTotalRows(int totalRows) throws IndexOutOfPageException {
		if (totalRows<0) {
			throw new IndexOutOfPageException("��¼���쳣");
		}
		page.setTotalRows(totalRows);
	}
	/**
	 * ���ÿҳ�����Ƿ��쳣
	 * @param pageSize
	 * @throws IndexOutOfPageException
	 */
	private void setPageSize(int pageSize) throws IndexOutOfPageException {
		if (pageSize<=0) {
			throw new IndexOutOfPageException("ÿҳ�����쳣");
		}
		page.setPageSize(pageSize);
	}
	/**
	 * ��ȡÿҳ����
	 * @return
	 */
	public int getPageSize(){
		return page.getPageSize();
	}
	/**
	 * ���õ�ǰҳ��,�������ĵ�ǰҳ���ǻ��׳��쳣�ġ�
	 * @param PageIndex
	 * @throws IndexOutOfPageException 
	 */
	public void setPageIndex(int pageIndex) throws IndexOutOfPageException{
		System.out.println("pageIndex:"+pageIndex+" pageSize:"+page.getPageSize());
		//�ж������Ƿ񳬳���Χ
		if(pageIndex < 0||pageIndex > page.getTotalPages()){
			throw new IndexOutOfPageException("ҳ��Ϊ"+pageIndex+"��ҳ��"+page.getTotalPages()+"ҳ���쳣");
		}else{
			page.setPageIndex(pageIndex);
		}
	}
	/**
	 * ��ȡ��ǰҳ
	 * @return
	 */
	public int getPageIndex(){
		return page.getPageIndex();
	}

	/**
	 * ��ȡ��ǰҳ����
	 * @return
	 */
	public List<T> getPageData(){
		return page.getPageData();
	}
	/**
	 * ���õ�ǰҳ������
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
