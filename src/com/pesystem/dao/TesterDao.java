package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Tester;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:50
 */
public interface TesterDao {


	/**
	 * ɾ��
	 * @param ������Ա���
	 */
	public boolean delete(String testerId);

	/**
	 * ����
	 * @param tester
	 * @return
	 */
	public boolean insert(List<Tester> testers);

	/**��ѯ
	 * 
	 * @param condition
	 */
	public List<Tester> select(String condition);

	/**
	 * ����
	 * @param tester
	 * @return
	 */
	public boolean update(Tester tester);
}