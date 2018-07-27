package com.pesystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Record;

public interface RecordDao {
	/**
	 * ɾ��
	 * @param record
	 * @return
	 */
	public boolean delete(Record record);

	/**
	 * ����
	 * @param record
	 * @return
	 * @throws SQLException 
	 */
	public boolean insert(List<Record> record);

	/**
	 * ��
	 * @param condition
	 * @return
	 */
	public List<Record> select(String condition);

	/**
	 * ��
	 * @param record
	 * @return
	 */
	public boolean update(Record record);
	
	/**
	 *ͨ��������Ա�ı�źͰ༶���ȷ��
	 * @param clazz
	 * @return
	 */
	public List<Record> selectByClass(Clazz clazz);
	
	/**
	 * ͨ����ʦ��źͰ༶����Լ����ȷ��������Ա
	 * @param peClass
	 * @return
	 */
	public List<Record> selectByPEClass(PEClass peClass);

}
