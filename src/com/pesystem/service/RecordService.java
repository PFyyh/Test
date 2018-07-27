package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Record;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public interface RecordService {

	/**
	 *  录入体测记录
	 * @param record
	 * @return
	 */
	boolean[] inputRecord(List<Record> records);
	
	/**
	 * 单个录入
	 * @param records
	 * @return
	 */
	boolean inputRecord(Record records);
	/**
	 * 分页
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	public String selectRecords(PageMethods<Record> pageMethods) throws IndexOutOfPageException;
	
	/**
	 * 测试人员获取测试学生记录
	 * @param tester
	 * @return
	 */
	List<Record> getTestStudentByClasses(Clazz clazz);
	/**
	 * 老师通过老师编号，班级编号和年份
	 * @param clazz
	 * @param year
	 * @return
	 */
	List<Record> getTestStudentByPEClasses(PEClass peClass);
	
	/**
	 * 分页显示记录
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getRecordByPage(PageMethods<Record> pageMethods) throws IndexOutOfPageException;
	
	/**
	 * 获取行数
	 * @return
	 */
	int count();
	
	/**
	 * 导入机考成绩
	 * @param file
	 * @return
	 */
	boolean[] importRecords(File file);
	
	/**
	 * 记录分页
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getRecordsByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * 删除记录
	 * @param stuNo
	 * @param year
	 * @return
	 */
	boolean deleteRecords(String stuNo,String year);
}
