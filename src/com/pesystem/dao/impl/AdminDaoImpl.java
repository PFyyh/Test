package com.pesystem.dao.impl;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pesystem.dao.AdminDao;
import com.pesystem.dao.MysqlBaseDao;
import com.pesystem.entity.Admin;

public class AdminDaoImpl implements AdminDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String adminId) {
		String sql = "DELETE FROM `pesystem`.`tb_admin` WHERE `adminId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, adminId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

	@Override
	public boolean insert(List<Admin> admins){
		connection = MysqlBaseDao.getAdminConnection();
		// int[] updateCounts; 记录日志
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO `pesystem`.`tb_admin`(`adminId`, `adminName`, `adminTel`, `adminPwd`, `adminEmail`) VALUES (?, ?, ?, md5(?), ?)");
			for (Admin temp : admins) {
				preparedStatement.setString(1, temp.getUserId());
				preparedStatement.setString(2, temp.getUserName());
				preparedStatement.setString(3, temp.getUserTel());
				preparedStatement.setString(4, temp.getUserPwd());
				preparedStatement.setString(5, temp.getUserEmail());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			try {
				connection.rollback();
			} catch (SQLException e2) {
			}
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return false;
		
	}

	@Override
	public List<Admin> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Admin> admins = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`tb_admin` where" + condition;
		System.out.println(sql);
		Admin admin = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				admin = new Admin();
				admin.setUserName(resultSet.getString("adminName"));
				admin.setUserEmail(resultSet.getString("adminEmail"));
				admin.setUserId(resultSet.getString("adminId"));
				admin.setUserTel(resultSet.getString("adminTel"));
				admin.setUserPwd(resultSet.getString("adminPwd"));
				admins.add(admin);
			}
			Iterator<Admin> iterator = admins.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return admins;
	}

	@Override
	public boolean update(Admin admin) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE `pesystem`.`tb_admin` SET `adminName` = ?, `adminTel` = ?, `adminPwd` = md5(?),adminEmail=? WHERE `adminId` = ?");
			preparedStatement.setString(1, admin.getUserName());
			preparedStatement.setString(2, admin.getUserTel());
			preparedStatement.setString(3, admin.getUserPwd());
			preparedStatement.setString(4, admin.getUserEmail());
			preparedStatement.setString(5, admin.getUserId());
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return false;
	}
	
	
}
