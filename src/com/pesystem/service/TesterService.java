package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface TesterService {
	/**
	 * ���²�����Ա��Ϣ
	 * 
	 * @param Tester
	 * @return
	 */
	boolean updateTester(Tester tester);
	
	/**
	 * ��������Ա���
	 * @param Tester
	 * @return
	 */
	boolean checkTester(Tester tester);
	
	/**
	 * ��ȡ������Ա���
	 * @return
	 */
	Tester getTester(Tester tester);
	
	/**
	 * ɾ��������Ա���
	 * @return
	 */
	boolean deleteTester(Tester tester);
	
	/**
	 * ����������Ա
	 * @return
	 */
	List<Tester> selectTester();
	
	/**
	 * ��ҳ����
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	String getTestersByPage(int pageSize, int pageIndex) throws IndexOutOfPageException; 
	
	/**
	 * ע�������Ա
	 * @param tester
	 * @return
	 */
	boolean addTester(Tester tester);
	
	/**
	 * ��������
	 * @return
	 */
	List<Tester> selectAll();
}
