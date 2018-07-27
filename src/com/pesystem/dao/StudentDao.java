package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Student;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:26
 */
public interface StudentDao {


	/**
	 * ɾ��
	 * @param ѧ��
	 * @return
	 */
	public boolean delete(String stuId);

	/**
	 * ��
	 * @param student
	 * @return
	 */
	public boolean insert(List<Student> students);

	/**
	 * ��
	 * @param ��ѯ����
	 * @return
	 */
	public List<Student> select(String condition);

	/**
	 * ��
	 * @param student
	 * @return
	 */
	public boolean update(Student student);
	
}