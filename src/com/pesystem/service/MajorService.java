package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Major;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public interface MajorService {
	
	/**
	 * ����רҵ��Ϣ
	 * @param major
	 * @return
	 */
	boolean updateMajor(Major major);
	
	/**
	 * ���רҵ
	 * @param major
	 * @return
	 */
	boolean addMajor(Major major);
	
	/**
	 * ���רҵ
	 * @param majors
	 * @return
	 */
	boolean addMajor(List<Major> majors);
	
	/**
	 * ɾ��רҵ
	 * @param majorId
	 * @return
	 */
	boolean deleteMajor(int majorId);
	
	/**
	 * ����רҵ
	 * @return
	 */
	List<Major> selectMajors();
	
	/**
	 * ��ȡ��¼����
	 * @return
	 */
	int getCount();
	
	/**
	 * ��ҳ����
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getMajorsByPage(PageMethods<Major> pageMethods) throws IndexOutOfPageException;
}
