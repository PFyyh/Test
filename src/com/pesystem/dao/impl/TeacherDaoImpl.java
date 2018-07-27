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
import com.pesystem.dao.TeacherDao;
import com.pesystem.entity.Teacher;

public class TeacherDaoImpl implements TeacherDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String teacherId) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_teacher` WHERE `teacherId` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		System.out.println("delete结果："+flag);
		return flag;
	}

	@Override
	public boolean insert(List<Teacher> teachers) {
		//INSERT INTO `pesystem`.`tb_teacher`(`teacherId`, `teacherName`, `teacherTel`, `teacherPwd`, `teacherEmail`) VALUES ('hewentao1997062630', '何文涛', '110', '110', '100')
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(
					"INSERT INTO `pesystem`.`tb_teacher`(`teacherId`, `teacherName`, `teacherTel`, `teacherPwd`, `teacherEmail`) VALUES (?, ?,?,md5(?),?)");
			for (Teacher temp : teachers) {
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
	public List<Teacher> select(String condition) {
		//SELECT * FROM `pesystem`.`tb_teacher` 
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Teacher> teachers = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`tb_teacher`  where" + condition;
		Teacher teacher = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				teacher = new Teacher();
				teacher.setUserEmail(resultSet.getString("teacherEmail"));
				teacher.setUserId(resultSet.getString("teacherId"));
				teacher.setUserName(resultSet.getString("teacherName"));
				teacher.setUserPwd(resultSet.getString("teacherPwd"));
				teacher.setUserTel(resultSet.getString("teacherTel"));
				teachers.add(teacher);
			}
			Iterator<Teacher> iterator = teachers.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return teachers;
	}

	@Override
	public boolean update(Teacher teacher) {
		System.out.println("TeacherDaoImpl.update()");
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		String sql = " UPDATE `pesystem`.`tb_teacher`"
				+ " SET `teacherName` = ?, `teacherTel` = ?,  `teacherEmail` = ? "
				+ "WHERE `teacherId` = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getUserName());
			preparedStatement.setString(2, teacher.getUserTel());
			preparedStatement.setString(3, teacher.getUserEmail());
			preparedStatement.setString(4, teacher.getUserId());
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

}
