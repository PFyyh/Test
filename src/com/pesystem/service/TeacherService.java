package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface TeacherService {
	/**
	 * ������ʦ��Ϣ
	 * 
	 * @param Teacher
	 * @return
	 */
	boolean updateTeacher(Teacher teacher);
	
	/**
	 * �����ʦ���
	 * @param Teacher
	 * @return
	 */
	boolean checkTeacher(Teacher teacher);
	
	/**
	 * ��ȡ��ʦ���
	 * @return
	 */
	Teacher getTeacher(Teacher teacher);
	
	/**
	 * ɾ����ʦ���
	 * @return
	 */
	boolean deleteTeacher(Teacher teacher);
	
	/**
	 * ������ʦ
	 * @return
	 */
	List<Teacher> selectTeacher(); 
	
	/**
	 * ��ҳ��ʾ��ʦ
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getTeachersByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * ע����ʦ
	 * @param teacher
	 * @return
	 */
	boolean addTeacher(Teacher teacher);
	
	/**
	 * ����
	 * @return
	 */
	List<Teacher> selectAll();
}
