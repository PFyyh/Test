package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Student;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:26
 */
public interface StudentDao {


	/**
	 * 删除
	 * @param 学号
	 * @return
	 */
	public boolean delete(String stuId);

	/**
	 * 插
	 * @param student
	 * @return
	 */
	public boolean insert(List<Student> students);

	/**
	 * 查
	 * @param 查询条件
	 * @return
	 */
	public List<Student> select(String condition);

	/**
	 * 改
	 * @param student
	 * @return
	 */
	public boolean update(Student student);
	
}