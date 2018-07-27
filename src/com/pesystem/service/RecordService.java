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
	 *  ¼������¼
	 * @param record
	 * @return
	 */
	boolean[] inputRecord(List<Record> records);
	
	/**
	 * ����¼��
	 * @param records
	 * @return
	 */
	boolean inputRecord(Record records);
	/**
	 * ��ҳ
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	public String selectRecords(PageMethods<Record> pageMethods) throws IndexOutOfPageException;
	
	/**
	 * ������Ա��ȡ����ѧ����¼
	 * @param tester
	 * @return
	 */
	List<Record> getTestStudentByClasses(Clazz clazz);
	/**
	 * ��ʦͨ����ʦ��ţ��༶��ź����
	 * @param clazz
	 * @param year
	 * @return
	 */
	List<Record> getTestStudentByPEClasses(PEClass peClass);
	
	/**
	 * ��ҳ��ʾ��¼
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getRecordByPage(PageMethods<Record> pageMethods) throws IndexOutOfPageException;
	
	/**
	 * ��ȡ����
	 * @return
	 */
	int count();
	
	/**
	 * ��������ɼ�
	 * @param file
	 * @return
	 */
	boolean[] importRecords(File file);
	
	/**
	 * ��¼��ҳ
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getRecordsByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * ɾ����¼
	 * @param stuNo
	 * @param year
	 * @return
	 */
	boolean deleteRecords(String stuNo,String year);
}
