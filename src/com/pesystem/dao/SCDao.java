package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.SC;

public interface SCDao {
	/**
	 * 
	 * @param sc
	 * @return
	 */
	public boolean delete(SC sc);

	/**
	 * ����
	 * @param tester
	 * @return
	 */
	public boolean insert(List<SC> scs);

	/**
	 * ��ѯ
	 * 
	 * @param condition
	 */
	public List<SC> select(String condition);

}
