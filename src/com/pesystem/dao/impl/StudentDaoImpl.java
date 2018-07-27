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
import com.pesystem.dao.StudentDao;
import com.pesystem.entity.Student;

public class StudentDaoImpl implements StudentDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(String stuId) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_student` WHERE `StuNo` =?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stuId);
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
	public boolean insert(List<Student> students) {
		System.out.println("StudentDaoImpl.insert()");
		connection = MysqlBaseDao.getAdminConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO `pesystem`.`tb_student`"
					+ "(`StuNo`, `ClassId`, `StuName`, `StuBirthday`, `StuPwd`, `PersonId`, `stuHome`, `StuSex`, `StuNation`, `stuOrigin`, `StuTel`, `stuEmail`) "
					+ "VALUES (?, ?, ?, ?,md5(?), ?,?, ?, ?, ?,?,?)");
			for (Student temp : students) {
				preparedStatement.setString(1, temp.getUserId());
				preparedStatement.setString(2, temp.getClassId());
				preparedStatement.setString(3, temp.getUserName());
				preparedStatement.setString(4, temp.getStuBirthday());
				preparedStatement.setString(5, temp.getUserPwd());
				preparedStatement.setString(6, temp.getPersonId());
				preparedStatement.setString(7, temp.getStuHome());
				preparedStatement.setInt(8, temp.getStuSex());
				if (temp.getStuNation()==null) {
					preparedStatement.setNull(9, java.sql.Types.INTEGER);
				}else {
					preparedStatement.setInt(9, temp.getStuNation());
				}
				if (temp.getStuNation()==null) {
					preparedStatement.setNull(9, java.sql.Types.INTEGER);
				}else {
					preparedStatement.setInt(9, temp.getStuOrigin());
				}
				preparedStatement.setInt(10, temp.getStuOrigin());
				preparedStatement.setString(11, temp.getUserTel());
				preparedStatement.setString(12, temp.getUserEmail());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			flag = true;
			System.out.println("StudentDaoImpl.insert():" + flag);
		} catch (SQLException e) {
			System.out.println(e);
			flag = false;
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
	public List<Student> select(String condition) {
		System.out.println("StudentDaoImpl.select()");
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_student` where " + condition;
		System.out.println("sql:" + sql);
		Student student = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				student = new Student();
				student.setClassId(resultSet.getString("classId"));
				student.setStuBirthday(resultSet.getString("stuBirthday"));
				student.setStuHome(resultSet.getString("stuHome"));
				student.setStuNation(resultSet.getInt("stuNation"));
				student.setStuOrigin(resultSet.getInt("stuOrigin"));
				student.setPersonId(resultSet.getString("personId"));
				student.setStuSex(resultSet.getInt("stuSex"));
				student.setUserEmail(resultSet.getString("stuEmail"));
				student.setUserId(resultSet.getString("stuNo"));
				student.setUserName(resultSet.getString("stuName"));
				student.setUserPwd(resultSet.getString("stuPwd"));
				student.setUserTel(resultSet.getString("stuTel"));
				student.setMajorName(resultSet.getString("MajorName"));
				student.setFacultyName(resultSet.getString("facultyName"));
				students.add(student);
			}
			Iterator<Student> iterator = students.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return students;
	}

	@Override
	public boolean update(Student student) {
		System.out.println("StudentDaoImpl.update()");
		System.out.println("------需要修改的对象--");
		System.out.println(student);
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		String sql = " UPDATE `pesystem`.`tb_student`"
				+ " SET `ClassId` = ?, `StuName` =?, `StuBirthday` = ?,  `PersonId` = ?, `stuHome` = ?, `StuSex` =?, `StuNation` = ?, `stuOrigin` = ?, `StuTel` =?, `stuEmail` = ?"
				+ "WHERE `StuNo` = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, student.getClassId());
			preparedStatement.setString(2, student.getUserName());
			preparedStatement.setString(3, student.getStuBirthday());
			preparedStatement.setString(4, student.getPersonId());
			preparedStatement.setString(5, student.getStuHome());
			preparedStatement.setInt(6, student.getStuSex());
			preparedStatement.setInt(7, student.getStuNation());
			preparedStatement.setInt(8, student.getStuOrigin());
			preparedStatement.setString(9, student.getUserTel());
			preparedStatement.setString(10, student.getUserEmail());
			preparedStatement.setString(11, student.getUserId());
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
			System.out.println("flag:"+flag);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

}
