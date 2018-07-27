package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Teacher;
import com.util.page.IndexOutOfPageException;

public interface PEClassService {
	/**
	 * ע��༶
	 * @param peClass
	 * @return
	 */
	boolean addPEClass(PEClass peClass);
	/**
	 * ��
	 * @param peclass
	 * @return
	 */
	public boolean addPEClass(List<PEClass> peclass);
	
	/**
	 *�������������༶
	 * @param file
	 * @return
	 */
	boolean importPEClasses(File file);
	
	/**
	 * ���ù���༶��ѧ��
	 * @param file
	 * @return
	 */
	boolean importStudents(File file);
	
	/**
	 * ���������༶��Ϣ
	 * @param peClass
	 * @return
	 */
	boolean updatePEClasses(PEClass peClass);
	
	/**
	 * ɾ�������༶
	 * @param peClass
	 * @return
	 */
	boolean deletePEClass(PEClass peClass);
	
	/**
	 * ��ѯ��ʦ�Ĺ���༶
	 * @param teacher
	 * @return
	 */
	List<PEClass> selectPEClasses(Teacher teacher);
	
	/**
	 * ��������
	 * @return
	 */
	List<PEClass> selectAll();
	
	/**
	 * ��ҳ
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getPEClassByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
}
