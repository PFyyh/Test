package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Clazz;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:32
 */
public interface ClassDao {

	/**
	 * ɾ���༶��Ϣ
	 * @param clazzId
	 * @return
	 */
	boolean delete(String clazzId);
	
	/**
	 * ע��༶
	 * @param clazz
	 * @return
	 */
	boolean insert(List<Clazz> clazz);
	
	/**
	 * ��ѯ�༶
	 * @param condition
	 * @return
	 */
	public List<Clazz> select(String condition);
	
	/**
	 * ���°༶
	 * @param clazz
	 * @return
	 */
	public boolean update(Clazz clazz);
}