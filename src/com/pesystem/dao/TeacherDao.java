package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Teacher;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:34
 */
public interface TeacherDao {
	/**
	 * 删除
	 * @param teacherId
	 * @return
	 */
	public boolean delete(String teacherId);
	/**
	 * 插入
	 * @param teacher
	 * @return
	 */
	public boolean insert(List<Teacher> teachers);
	/**
	 * 查
	 * @param condition
	 * @return
	 */
	public List<Teacher> select(String condition);
	/**
	 * 更新
	 * @param teacher
	 * @return
	 */
	public boolean update(Teacher teacher);
	
}