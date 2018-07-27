package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.PEClass;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:33
 */
public interface PEClassDao {

	/**
	 * 删除
	 * @param peClassId
	 * @return
	 */
	public boolean delete(String peClassId);
	/**
	 * 增
	 * @param peclass
	 * @return
	 */
	public boolean insert(List<PEClass> peclass);
	/**
	 * 查
	 * @param condition
	 * @return
	 */
	public List<PEClass> select(String condition);

	/**
	 * 改
	 * @param peClass
	 * @return
	 */
	public boolean update(PEClass peClass);
}