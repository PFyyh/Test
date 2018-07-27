package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Record;
import com.pesystem.entity.Student;
import com.util.page.IndexOutOfPageException;

public interface StudentService {
	/**
	 * ����ѧ��������Ϣ
	 * @param student
	 * @return
	 */
	boolean updateStudentInfor(Student student); 
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param student
	 * @return
	 */
	Student selectStudentInfor(Student student);
	
	/**
	 * ���ѧ���Ƿ����
	 * @param studentId
	 * @return
	 */
	boolean checkStudentAccount(String studentId);
	
	/**
	 * �鿴ѧ���ɼ�
	 * @param studentId
	 * @return
	 */
	List<Record> selectRecords(String studentId);
	
	/**
	 * ����ѧ��
	 * @return
	 */
	List<Student> selectStudents();
	
	/**
	 * ͨ��Excel�ļ���������
	 * @param file
	 * @return
	 */
	boolean importStudentsByExcel(File file);
	
	/**
	 * ��ҳ��ʾѧ��
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getStudentsByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * ���ѧ��
	 * @return
	 */
	boolean insertStudent(Student student);
	
}
