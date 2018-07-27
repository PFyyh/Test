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
import com.pesystem.dao.TesterDao;
import com.pesystem.entity.Tester;

public class TesterDaoImpl implements TesterDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String testerId) {
		// DELETE FROM `pesystem`.`tb_tester` WHERE `testerId` = 'yyh111'
		String sql = "DELETE FROM `pesystem`.`tb_tester` WHERE `testerId` = ?";
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, testerId);
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
	public boolean insert(List<Tester> testers) {
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO `pesystem`.`tb_tester`(`testerId`, `testerName`, `testerTel`, `testerPwd`, `testerEmail`) VALUES (?, ?,?,md5(?),?)");
			for (Tester temp : testers) {
				preparedStatement.setString(1, temp.getUserId());
				preparedStatement.setString(2, temp.getUserName());
				preparedStatement.setString(3, temp.getUserTel());
				preparedStatement.setString(4, temp.getUserPwd());
				preparedStatement.setString(5, temp.getUserEmail());
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
	public List<Tester> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Tester> testers = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`tb_tester`  where" + condition;
		Tester tester = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				tester = new Tester();
				tester.setUserEmail(resultSet.getString("testerEmail"));
				tester.setUserId(resultSet.getString("testerId"));
				tester.setUserName(resultSet.getString("testerName"));
				tester.setUserPwd(resultSet.getString("testerPwd"));
				tester.setUserTel(resultSet.getString("testerTel"));
				testers.add(tester);
			}
			Iterator<Tester> iterator = testers.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return testers;
	}

	@Override
	public boolean update(Tester tester) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		boolean flag = false;
		String sql = " UPDATE `pesystem`.`tb_tester`"
				+ " SET `testerName` = ?, `testerTel` = ?,  `testerEmail` = ? "
				+ "WHERE `testerId` = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, tester.getUserName());
			preparedStatement.setString(2, tester.getUserTel());
			preparedStatement.setString(3, tester.getUserEmail());
			preparedStatement.setString(4, tester.getUserId());
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
