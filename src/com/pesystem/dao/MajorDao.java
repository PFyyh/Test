package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Major;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:34
 */
public interface MajorDao {

	/**
	 * ɾ��רҵ
	 * @param רҵ���
	 */
	public boolean delete(int majorId);

	/**
	 * ע����רҵ
	 * @param רҵ
	 */
	public boolean insert(List<Major> majors);

	/**
	 * ��ѯ����������רҵ
	 * @param condition
	 */
	public List<Major> select(String condition);

	/**
	 * @param רҵ
	 */
	public boolean update(Major major);
}