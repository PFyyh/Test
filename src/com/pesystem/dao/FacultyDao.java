package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Faculty;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:27
 */
public interface FacultyDao {
	
	/**
	 * ɾ��ѧԺ
	 * @param faculty
	 * @return
	 */
	public boolean delete(int facultyId);
	/**
	 * ע��ѧԺ
	 * @param faculty
	 * @return
	 */
	public boolean insert(List<Faculty> faculty);
	/**
	 * ��ѯ�ض�����
	 * @param condition
	 * @return
	 */
	public List<Faculty> select(String condition);
	
	/**
	 * ����ѧԺ����
	 * @param faculty
	 * @return
	 */
	public boolean update(Faculty faculty);
}