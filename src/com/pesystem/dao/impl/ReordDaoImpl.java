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
import com.pesystem.dao.RecordDao;
import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Record;

public class ReordDaoImpl implements RecordDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;// 创建数据集
	private ResultSet resultSet = null;// 创建结果

	@Override
	public boolean delete(Record record) {
		boolean flag = false;
		String sql = "DELETE FROM `pesystem`.`tb_record` WHERE `Year` = ? AND `StuNo` = ?";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, record.getYear());
			preparedStatement.setString(2, record.getStuNo());
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
	public boolean insert(List<Record> record){
		boolean flag= false;
		connection = MysqlBaseDao.getAdminConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(" INSERT INTO `pesystem`.`tb_record`"
					+ "(`Year`, `StuNo`, `Height`, `Weight`, `VitalCapacity`, `BeginToBend`, `StandingBroadJump`, `SitUp`, `fivetyRun`, `thousandRun`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?)");
			for (Record temp : record) {
				preparedStatement.setInt(1, temp.getYear());
				preparedStatement.setString(2, temp.getStuNo());
				if(temp.getHeight()==null){
					preparedStatement.setNull(3, java.sql.Types.DECIMAL);
				}else{
					preparedStatement.setDouble(3, temp.getHeight());
				}
				if(temp.getWeight()==null){
					preparedStatement.setNull(4, java.sql.Types.DECIMAL);
				}else{
					preparedStatement.setDouble(4, temp.getWeight());
				}
				if(temp.getVitalcapacity()==null){
					preparedStatement.setNull(5, java.sql.Types.INTEGER);
				}else{
					preparedStatement.setDouble(5, temp.getVitalcapacity());
				}
				if(temp.getBeginToBend()==null){
					preparedStatement.setNull(6, java.sql.Types.DECIMAL);
				}else{
					preparedStatement.setDouble(6, temp.getBeginToBend());
				}
				if(temp.getStandingBroadJump()==null){
					preparedStatement.setNull(7, java.sql.Types.DECIMAL);
				}else{
					preparedStatement.setDouble(7, temp.getStandingBroadJump());
				}
				if(temp.getSitup()==null){
					preparedStatement.setNull(8, java.sql.Types.INTEGER);
				}else{
					preparedStatement.setInt(8, temp.getSitup());
				}
				if(temp.getFivetyRun()==null){
					preparedStatement.setNull(9, java.sql.Types.VARCHAR);
				}else{
					preparedStatement.setString(9, temp.getFivetyRun());
				}
				if(temp.getThousandRun()==null){
					preparedStatement.setNull(10, java.sql.Types.VARCHAR);
				}else{
					preparedStatement.setString(10, temp.getThousandRun());
				}
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			flag = true;
		} catch (SQLException e) {
			System.out.println("异常"+e);
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
	public List<Record> select(String condition) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Record> records = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_record` where " + condition;
		Record record = null;
		try {
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				record = new Record();
				record.setBeginToBend(resultSet.getDouble("beginToBend"));
				record.setFivetyRun(resultSet.getString("fivetyRun"));
				record.setHeight(resultSet.getDouble("height"));
				record.setSitup(resultSet.getInt("situp"));
				record.setStandingBroadJump(resultSet.getDouble("standingBroadJump"));
				record.setStuNo(resultSet.getString("stuNo"));
				record.setThousandRun(resultSet.getString("thousandRun"));
				record.setVitalcapacity(resultSet.getInt("vitalCapacity"));
				record.setWeight(resultSet.getDouble("weight"));
				record.setYear(resultSet.getInt("year"));
				record.setUserName(resultSet.getString("stuName"));
				records.add(record);
			}
			Iterator<Record> iterator = records.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return records;
	}

	@Override
	public boolean update(Record record) {
		boolean flag = false;
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		String sql = "UPDATE `pesystem`.`tb_record` "
				+ "SET `Height` = ?, `Weight` = ?, `VitalCapacity` = ?, `BeginToBend` = ?, `StandingBroadJump` = ?, `SitUp` = ?, `fivetyRun` = ?, `thousandRun` = ?"
				+ " WHERE `Year` = ? AND `StuNo` = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, record.getHeight());
			preparedStatement.setDouble(2, record.getWeight());
			preparedStatement.setInt(3, record.getVitalcapacity());
			preparedStatement.setDouble(4, record.getBeginToBend());
			preparedStatement.setDouble(5, record.getStandingBroadJump());
			preparedStatement.setInt(6, record.getSitup());
			preparedStatement.setString(7, record.getFivetyRun());
			preparedStatement.setString(8, record.getThousandRun());
			preparedStatement.setInt(9, record.getYear());
			preparedStatement.setString(10, record.getStuNo());
			preparedStatement.addBatch();
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
	public List<Record> selectByClass(Clazz clazz) {
		//SELECT * FROM `pesystem`.`vw_peclasstest` where teacherId="hewentao" and year="2018" and peclassId="123";
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Record> records = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_classtest` where testerId=? and year=? and classId=?;";
		Record record = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clazz.getClassId());
			preparedStatement.setInt(2, clazz.getClassYear());
			preparedStatement.setString(3, clazz.getClassId());
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				record = new Record();
				record.setBeginToBend(resultSet.getDouble("beginToBend"));
				record.setFivetyRun(resultSet.getString("fivetyRun"));
				record.setHeight(resultSet.getDouble("height"));
				record.setSitup(resultSet.getInt("situp"));
				record.setStandingBroadJump(resultSet.getDouble("standingBroadJump"));
				record.setStuNo(resultSet.getString("stuNo"));
				record.setThousandRun(resultSet.getString("thousandRun"));
				record.setVitalcapacity(resultSet.getInt("vitalCapacity"));
				record.setWeight(resultSet.getDouble("weight"));
				record.setYear(resultSet.getInt("year"));
				records.add(record);
			}
			Iterator<Record> iterator = records.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return records;
	}

	@Override
	public List<Record> selectByPEClass(PEClass peClass) {
		connection = MysqlBaseDao.getAdminConnection();// 连接数据库
		List<Record> records = new ArrayList<>();
		String sql = "SELECT * FROM `pesystem`.`vw_peclasstest` where teacherId=? and year=? and peclassId=?";
		Record record = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, peClass.getClassId());
			preparedStatement.setInt(2, peClass.getClassYear());
			preparedStatement.setString(3, peClass.getClassId());
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				record = new Record();
				record.setBeginToBend(resultSet.getDouble("beginToBend"));
				record.setFivetyRun(resultSet.getString("fivetyRun"));
				record.setHeight(resultSet.getDouble("height"));
				record.setSitup(resultSet.getInt("situp"));
				record.setStandingBroadJump(resultSet.getDouble("standingBroadJump"));
				record.setStuNo(resultSet.getString("stuNo"));
				record.setThousandRun(resultSet.getString("thousandRun"));
				record.setVitalcapacity(resultSet.getInt("vitalCapacity"));
				record.setWeight(resultSet.getDouble("weight"));
				record.setYear(resultSet.getInt("year"));
				records.add(record);
			}
			Iterator<Record> iterator = records.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MysqlBaseDao.close(resultSet, preparedStatement, connection);
		}
		return records;
	}
	
}
