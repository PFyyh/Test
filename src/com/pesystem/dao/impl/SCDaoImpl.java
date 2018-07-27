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
import com.pesystem.dao.SCDao;
import com.pesystem.entity.SC;

public class SCDaoImpl implements SCDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果
	@Override
	public boolean delete(SC sc) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_sc` WHERE `StuNo` = ? AND `PEClassId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sc.getStuNo());
			preparedStatement.setString(2, sc.getPEClassId());
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
	public boolean insert(List<SC> scs) {
		// INSERT INTO `pesystem`.`tb_sc`(`StuNo`, `PEClassId`) VALUES ('1501511625', '123')
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					" INSERT INTO `pesystem`.`tb_sc`(`StuNo`, `PEClassId`) VALUES (?, ?)");
			for (SC temp : scs) {
				preparedStatement.setString(1, temp.getStuNo());
				preparedStatement.setString(2, temp.getPEClassId());
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
	public List<SC> select(String condition) {
		// SELECT * FROM `pesystem`.`tb_sc` LIMIT 0, 1000
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<SC> scs = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`tb_sc` where" + condition;
		SC sc = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				sc = new SC();
				sc.setStuNo(resultSet.getString("stuNo"));
				sc.setPEClassId(resultSet.getString("classId"));
				scs.add(sc);
			}
			Iterator<SC> iterator = scs.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return scs;
	}

}
