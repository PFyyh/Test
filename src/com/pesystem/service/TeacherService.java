package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface TeacherService {
	/**
	 * 更新老师信息
	 * 
	 * @param Teacher
	 * @return
	 */
	boolean updateTeacher(Teacher teacher);
	
	/**
	 * 检查老师身份
	 * @param Teacher
	 * @return
	 */
	boolean checkTeacher(Teacher teacher);
	
	/**
	 * 获取老师身份
	 * @return
	 */
	Teacher getTeacher(Teacher teacher);
	
	/**
	 * 删除老师身份
	 * @return
	 */
	boolean deleteTeacher(Teacher teacher);
	
	/**
	 * 遍历老师
	 * @return
	 */
	List<Teacher> selectTeacher(); 
	
	/**
	 * 分页显示老师
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getTeachersByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * 注册老师
	 * @param teacher
	 * @return
	 */
	boolean addTeacher(Teacher teacher);
	
	/**
	 * 遍历
	 * @return
	 */
	List<Teacher> selectAll();
}
