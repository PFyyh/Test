package com.pesystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pesystem.dao.FacultyDao;
import com.pesystem.dao.MysqlBaseDao;
import com.pesystem.entity.Faculty;

public class FacultyDaoImpl implements FacultyDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(int facultyId) {
		System.out.println(facultyId);
		String sql = "DELETE FROM `pesystem`.`tb_faculty` WHERE `FacultyId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, facultyId);
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
	public boolean insert(List<Faculty> faculty) {
		System.out.println("FacultyDaoImpl.insert()");
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO `pesystem`.`tb_faculty`(`FacultyName`) VALUES (?)");
			for (Faculty temp : faculty) {
				preparedStatement.setString(1, temp.getFacultyName());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			flag = true;
		} catch (SQLException e) {
			System.out.println(e);
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
	public List<Faculty> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Faculty> faculties = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`tb_faculty` where " + condition;
		Faculty faculty = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				faculty = new Faculty();
				faculty.setFacultyName(resultSet.getString("facultyName"));
				faculty.setFacultyId(resultSet.getInt("facultyId"));
				faculties.add(faculty);
			}
			Iterator<Faculty> iterator = faculties.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return faculties;
	}

	@Override
	public boolean update(Faculty faculty) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		boolean flag = false;
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE `pesystem`.`tb_faculty` SET `FacultyName` = ? WHERE `FacultyId` = ?");
			preparedStatement.setString(1, faculty.getFacultyName());
			preparedStatement.setInt(2, faculty.getFacultyId());
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

}
