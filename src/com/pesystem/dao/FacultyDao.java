package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Faculty;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:27
 */
public interface FacultyDao {
	
	/**
	 * 删除学院
	 * @param faculty
	 * @return
	 */
	public boolean delete(int facultyId);
	/**
	 * 注册学院
	 * @param faculty
	 * @return
	 */
	public boolean insert(List<Faculty> faculty);
	/**
	 * 查询特定条件
	 * @param condition
	 * @return
	 */
	public List<Faculty> select(String condition);
	
	/**
	 * 更新学院名字
	 * @param faculty
	 * @return
	 */
	public boolean update(Faculty faculty);
}