package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Clazz;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:32
 */
public interface ClassDao {

	/**
	 * 删除班级信息
	 * @param clazzId
	 * @return
	 */
	boolean delete(String clazzId);
	
	/**
	 * 注册班级
	 * @param clazz
	 * @return
	 */
	boolean insert(List<Clazz> clazz);
	
	/**
	 * 查询班级
	 * @param condition
	 * @return
	 */
	public List<Clazz> select(String condition);
	
	/**
	 * 更新班级
	 * @param clazz
	 * @return
	 */
	public boolean update(Clazz clazz);
}