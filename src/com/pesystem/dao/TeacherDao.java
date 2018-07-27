package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Teacher;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:34
 */
public interface TeacherDao {
	/**
	 * ɾ��
	 * @param teacherId
	 * @return
	 */
	public boolean delete(String teacherId);
	/**
	 * ����
	 * @param teacher
	 * @return
	 */
	public boolean insert(List<Teacher> teachers);
	/**
	 * ��
	 * @param condition
	 * @return
	 */
	public List<Teacher> select(String condition);
	/**
	 * ����
	 * @param teacher
	 * @return
	 */
	public boolean update(Teacher teacher);
	
}