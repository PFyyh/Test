package com.pesystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pesystem.dao.MajorDao;
import com.pesystem.dao.MysqlBaseDao;
import com.pesystem.entity.Major;

public class MajorDaoImpl implements MajorDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(int majorId) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_major` WHERE `MajorId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, majorId);
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
	public boolean insert(List<Major> majors) {
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO `pesystem`.`tb_major`(`FacultyId`, `MajorName`) VALUES (?,?)");
			for (Major temp : majors) {
				preparedStatement.setInt(1, temp.getFacultyId());
				preparedStatement.setString(2, temp.getMajorName());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			flag = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
			}
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

	@Override
	public List<Major> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Major> majors = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_major` where " + condition;
		System.out.println(sql);
		Major major = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				major = new Major();
				major.setFacultyId(resultSet.getInt("facultyId"));
				major.setMajorId(resultSet.getInt("majorId"));
				major.setMajorName(resultSet.getString("majorName"));
				major.setFacultyName(resultSet.getString("facultyName"));
				System.out.println("mjj:"+major);
				majors.add(major);
			}
			Iterator<Major> iterator = majors.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return majors;
	}

	@Override
	public boolean update(Major major) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		System.out.println("------------------");
		System.out.println("major:"+major);
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE `pesystem`.`tb_major` SET `FacultyId` = ?, `MajorName` = ? WHERE `MajorId` = ?");
			preparedStatement.setInt(1, major.getFacultyId());
			preparedStatement.setString(2, major.getMajorName());
			preparedStatement.setInt(3, major.getMajorId());
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
			System.out.println("daO:"+flag);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

}
