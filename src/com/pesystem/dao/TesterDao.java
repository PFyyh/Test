package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Tester;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:50
 */
public interface TesterDao {


	/**
	 * 删除
	 * @param 测试人员编号
	 */
	public boolean delete(String testerId);

	/**
	 * 插入
	 * @param tester
	 * @return
	 */
	public boolean insert(List<Tester> testers);

	/**查询
	 * 
	 * @param condition
	 */
	public List<Tester> select(String condition);

	/**
	 * 更新
	 * @param tester
	 * @return
	 */
	public boolean update(Tester tester);
}