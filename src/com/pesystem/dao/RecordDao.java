package com.pesystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Record;

public interface RecordDao {
	/**
	 * 删除
	 * @param record
	 * @return
	 */
	public boolean delete(Record record);

	/**
	 * 插入
	 * @param record
	 * @return
	 * @throws SQLException 
	 */
	public boolean insert(List<Record> record);

	/**
	 * 查
	 * @param condition
	 * @return
	 */
	public List<Record> select(String condition);

	/**
	 * 改
	 * @param record
	 * @return
	 */
	public boolean update(Record record);
	
	/**
	 *通过测试人员的编号和班级编号确定
	 * @param clazz
	 * @return
	 */
	public List<Record> selectByClass(Clazz clazz);
	
	/**
	 * 通过老师编号和班级编号以及年份确定测试人员
	 * @param peClass
	 * @return
	 */
	public List<Record> selectByPEClass(PEClass peClass);

}
