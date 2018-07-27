package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Record;
import com.pesystem.entity.Student;
import com.util.page.IndexOutOfPageException;

public interface StudentService {
	/**
	 * 更新学生个人信息
	 * @param student
	 * @return
	 */
	boolean updateStudentInfor(Student student); 
	
	/**
	 * 获取学生信息
	 * @param student
	 * @return
	 */
	Student selectStudentInfor(Student student);
	
	/**
	 * 检查学号是否存在
	 * @param studentId
	 * @return
	 */
	boolean checkStudentAccount(String studentId);
	
	/**
	 * 查看学生成绩
	 * @param studentId
	 * @return
	 */
	List<Record> selectRecords(String studentId);
	
	/**
	 * 遍历学生
	 * @return
	 */
	List<Student> selectStudents();
	
	/**
	 * 通过Excel文件批量导入
	 * @param file
	 * @return
	 */
	boolean importStudentsByExcel(File file);
	
	/**
	 * 分页显示学生
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getStudentsByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
	
	/**
	 * 添加学生
	 * @return
	 */
	boolean insertStudent(Student student);
	
}
