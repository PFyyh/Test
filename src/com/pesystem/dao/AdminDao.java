package com.pesystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pesystem.entity.Admin;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-六月-2018 8:34:35
 */
public interface AdminDao {

	/**
	 * 删除管理员
	 * @param adminId
	 * @return
	 */
	boolean delete(String adminId);

	/**
	 * 注册管理员
	 * @param admins
	 * @return
	 * @throws SQLException
	 */
	boolean insert(List<Admin> admins);

	/**
	 * 查询，condition自己组装条件
	 * @param condition
	 * @return
	 */
	public List<Admin> select(String condition);

	/**
	 * 更新管理员信息
	 * @param admin
	 * @return
	 */
	public boolean update(Admin admin);
}