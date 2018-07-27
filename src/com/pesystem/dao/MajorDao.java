package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.Major;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:34
 */
public interface MajorDao {

	/**
	 * 删除专业
	 * @param 专业编号
	 */
	public boolean delete(int majorId);

	/**
	 * 注册新专业
	 * @param 专业
	 */
	public boolean insert(List<Major> majors);

	/**
	 * 查询符合条件的专业
	 * @param condition
	 */
	public List<Major> select(String condition);

	/**
	 * @param 专业
	 */
	public boolean update(Major major);
}