package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface ClassService {
	/**
	 * ע��༶
	 * 
	 * @param clazz
	 * @return
	 */
	boolean addClass(Clazz clazz);

	/**
	 * ע��༶
	 * 
	 * @param clazz
	 * @return
	 */
	boolean addClass(List<Clazz> clazz);

	/**
	 * ɾ���༶
	 * 
	 * @param clazzId
	 * @return
	 */
	boolean deleteClazz(String clazzId);

	/**
	 * ���°༶
	 * 
	 * @param clazz
	 * @return
	 */
	boolean updateClazz(Clazz clazz);

	/**
	 * ��ҳ�����༶
	 * 
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String geClassesByPage(int pageSize, int pageIndex) throws IndexOutOfPageException;

	/**
	 * ͨ��������Ա��Ż�ȡ�����԰༶
	 * 
	 * @param tester
	 * @return
	 */
	List<Clazz> selectClasses(Tester tester);

	/**
	 * �������������༶
	 * 
	 * @param file
	 * @return
	 */
	boolean importClasses(File file);

	/**
	 * ����Ա����ĳ���༶
	 * @param clazz
	 * @return
	 */
	boolean setTester(Clazz clazz);
	
	/**
	 * ��������
	 * @return
	 */
	List<Clazz> selectAll();
}
