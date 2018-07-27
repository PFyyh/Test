package com.pesystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pesystem.dao.ClassDao;
import com.pesystem.dao.MysqlBaseDao;
import com.pesystem.entity.Clazz;

public class ClassDaoImpl implements ClassDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String clazzId) {
		String sql = "DELETE FROM `pesystem`.`tb_class` WHERE `ClassId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clazzId);
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return false;
	}

	@Override
	public boolean insert(List<Clazz> clazz) {
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO `pesystem`.`tb_class`(`ClassId`, `MajorId`, `ClassName`,	`ClassYear`) VALUES (?, ?, ?, ?)");
			for (Clazz temp : clazz) {
				preparedStatement.setString(1, temp.getClassId());
				preparedStatement.setInt(2, temp.getMajorId());
				preparedStatement.setString(3, temp.getClassName());
				preparedStatement.setInt(4, temp.getClassYear());
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
	public List<Clazz> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Clazz> clazzs = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_class` where " + condition;
		Clazz clazz = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				clazz = new Clazz();
				clazz.setClassId(resultSet.getString("classId"));
				clazz.setClassName(resultSet.getString("className"));
				clazz.setMajorId(resultSet.getInt("majorId"));
				clazz.setClassYear(resultSet.getInt("classYear"));
				clazz.setTesterId(resultSet.getString("TesterId"));
				clazz.setMajorName(resultSet.getString("majorName"));
				clazz.setTesterName(resultSet.getString("TesterName"));
				clazzs.add(clazz);
			}
			Iterator<Clazz> iterator = clazzs.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return clazzs;
	}

	@Override
	public boolean update(Clazz clazz) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		String sql = "UPDATE `pesystem`.`tb_class` SET testerId = ?, `ClassName` = ?, `ClassYear` = ?,MajorId = ? WHERE `ClassId` = ?";
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (clazz.getTesterId()==null) {
				preparedStatement.setNull(1, java.sql.Types.VARCHAR);
			}else{
				preparedStatement.setString(1, clazz.getTesterId());
			}
			preparedStatement.setString(2, clazz.getClassName());
			preparedStatement.setInt(3, clazz.getClassYear());
			preparedStatement.setInt(4, clazz.getMajorId());
			preparedStatement.setString(5, clazz.getClassId());
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
