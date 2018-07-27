package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.PEClass;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:33
 */
public interface PEClassDao {

	/**
	 * ɾ��
	 * @param peClassId
	 * @return
	 */
	public boolean delete(String peClassId);
	/**
	 * ��
	 * @param peclass
	 * @return
	 */
	public boolean insert(List<PEClass> peclass);
	/**
	 * ��
	 * @param condition
	 * @return
	 */
	public List<PEClass> select(String condition);

	/**
	 * ��
	 * @param peClass
	 * @return
	 */
	public boolean update(PEClass peClass);
}