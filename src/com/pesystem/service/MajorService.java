package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Major;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public interface MajorService {
	
	/**
	 * 更新专业信息
	 * @param major
	 * @return
	 */
	boolean updateMajor(Major major);
	
	/**
	 * 添加专业
	 * @param major
	 * @return
	 */
	boolean addMajor(Major major);
	
	/**
	 * 添加专业
	 * @param majors
	 * @return
	 */
	boolean addMajor(List<Major> majors);
	
	/**
	 * 删除专业
	 * @param majorId
	 * @return
	 */
	boolean deleteMajor(int majorId);
	
	/**
	 * 遍历专业
	 * @return
	 */
	List<Major> selectMajors();
	
	/**
	 * 获取记录行数
	 * @return
	 */
	int getCount();
	
	/**
	 * 分页遍历
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getMajorsByPage(PageMethods<Major> pageMethods) throws IndexOutOfPageException;
}
