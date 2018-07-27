package com.pesystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pesystem.dao.MysqlBaseDao;
import com.pesystem.dao.PEClassDao;
import com.pesystem.entity.PEClass;

public class PEClassDaoImpl implements PEClassDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String peClassId) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_admin` WHERE `adminId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, peClassId);
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
	public boolean insert(List<PEClass> peclass) {
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					" INSERT INTO `pesystem`.`tb_peclass`(`PEClassId`, `PEClassName`,`PEClassTime`,`TeacherId`) VALUES (?, ?, ?,?)");
			for (PEClass temp : peclass) {
				preparedStatement.setString(1, temp.getClassId());
				preparedStatement.setString(2, temp.getClassName());
				preparedStatement.setInt(3, temp.getClassYear());
				if (temp.getTesterId()==null||"".equals(temp.getTesterId())) {
					preparedStatement.setNull(4, java.sql.Types.VARCHAR);
				}else{
					preparedStatement.setString(4, temp.getTesterId());
				}
				
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
	public List<PEClass> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<PEClass> peClasses = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_peclass` where" + condition;
		PEClass peClass = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				peClass = new PEClass();
				peClass.setClassId(resultSet.getString("peclassId"));
				peClass.setClassName(resultSet.getString("peclassName"));
				peClass.setClassYear(resultSet.getInt("peclassTime"));
				peClass.setTesterId(resultSet.getString("teacherId"));
				peClass.setTesterName(resultSet.getString("teacherName"));
				peClasses.add(peClass);
			}
			Iterator<PEClass> iterator = peClasses.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return peClasses;
	}

	@Override
	public boolean update(PEClass peClass) {
		System.out.println("updateRecord----------------------");
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		String sql = "UPDATE `pesystem`.`tb_peclass` SET teacherId=?,`PEClassName` =?, `PEClassTime` = ? WHERE `PEClassId` = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, peClass.getTesterId());
			preparedStatement.setString(2, peClass.getClassName());
			preparedStatement.setInt(3, peClass.getClassYear());
			preparedStatement.setString(4, peClass.getClassId());
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
			System.out.println("update:"+flag);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

}
