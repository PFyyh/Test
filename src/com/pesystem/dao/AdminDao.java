package com.pesystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pesystem.entity.Admin;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:35
 */
public interface AdminDao {

	/**
	 * ɾ������Ա
	 * @param adminId
	 * @return
	 */
	boolean delete(String adminId);

	/**
	 * ע�����Ա
	 * @param admins
	 * @return
	 * @throws SQLException
	 */
	boolean insert(List<Admin> admins);

	/**
	 * ��ѯ��condition�Լ���װ����
	 * @param condition
	 * @return
	 */
	public List<Admin> select(String condition);

	/**
	 * ���¹���Ա��Ϣ
	 * @param admin
	 * @return
	 */
	public boolean update(Admin admin);
}