package com.pesystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.RecordDao;
import com.pesystem.dao.StudentDao;
import com.pesystem.dao.impl.ReordDaoImpl;
import com.pesystem.dao.impl.StudentDaoImpl;
import com.pesystem.entity.Record;
import com.pesystem.entity.Student;
import com.pesystem.service.StudentService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.poi.method.ExcelAPI;

public class StudentServiceImpl implements StudentService {
	StudentDao studentDao = new StudentDaoImpl();
	RecordDao recordDao = new ReordDaoImpl();

	@Override
	public boolean updateStudentInfor(Student student) {
		System.out.println("StudentServiceImpl.updateStudentInfor()");
		boolean flag = false;
		List<Student> students = studentDao.select("stuNo=\"" + student.getUserId()+"\"");
		for (Student student2 : students) {
			System.out.println("asd:"+student2);
		}
		if (students.size() == 1) {
			setUpdateStudent(students.get(0), student);
			flag = studentDao.update(student);
		}
		return flag;
	}

	private static void setUpdateStudent(Student oldStudent, Student newStudent) {
		if (newStudent.getUserEmail() != null && !"".equals(newStudent.getUserEmail())) {
			oldStudent.setUserEmail(newStudent.getUserEmail());
		}
		if (newStudent.getUserName() != null && !"".equals(newStudent.getUserName())) {
			oldStudent.setUserName(newStudent.getUserName());
		}
		if (newStudent.getUserPwd() != null && !"".equals(newStudent.getUserPwd())) {
			oldStudent.setUserPwd(newStudent.getUserPwd());
		}
		if (newStudent.getUserTel() != null && !"".equals(newStudent.getUserTel())) {
			oldStudent.setUserTel(newStudent.getUserTel());
		}
		if (newStudent.getClassId() != null && !"".equals(newStudent.getClassId())) {
			oldStudent.setClassId(newStudent.getClassId());
		}
		if (newStudent.getStuBirthday() != null) {
			oldStudent.setStuBirthday(newStudent.getStuBirthday());
		}
		if (newStudent.getStuHome() != null && !"".equals(newStudent.getStuHome())) {
			oldStudent.setStuHome(newStudent.getStuHome());
		}
		if (newStudent.getStuNation() != null && !"".equals(newStudent.getStuNation())) {
			oldStudent.setStuNation(newStudent.getStuNation());
		}
		if (newStudent.getPersonId() != null && !"".equals(newStudent.getPersonId())) {
			oldStudent.setPersonId(newStudent.getPersonId());
		}
		if (newStudent.getStuSex() != null) {
			oldStudent.setStuSex(newStudent.getStuSex());
		}
		if (newStudent.getStuOrigin() != null && !"".equals(newStudent.getStuOrigin())) {
			oldStudent.setStuOrigin(newStudent.getStuOrigin());
		}

	}

	@Override
	public Student selectStudentInfor(Student student) {
		if (student==null) {
			return null;
		}
		List<Student> students = studentDao.select(" stuNo=\"" + student.getUserId() + "\" and stuPwd=md5(\"" + student.getUserPwd()+"\")");
		if (students.size()==0) {
			return null;
		}
		return students.get(0);
	}

	@Override
	public boolean checkStudentAccount(String studentId) {
		List<Student> students = studentDao.select("stuNo=" + studentId);
		boolean flag = false;
		if (students.size() == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Record> selectRecords(String studentId) {
		return recordDao.select(" stuNo=\"" + studentId+"\"");
	}

	@Override
	public List<Student> selectStudents() {
		return studentDao.select("true");
	}

	@Override
	public boolean importStudentsByExcel(File file) {
		boolean flag = false;
		try {
			ExcelAPI excelApi = new ExcelAPI(file);
			excelApi.openExcel();
			List<List<String>> data = excelApi.getData();
			if (!checkTempleteFile(data.get(0))) {
				return flag;
			}
			for (List<String> row : data) {
				for (String string : row) {
					System.out.print(string + "->");
				}
				System.out.println();
			}
			List<Student> students = new ArrayList<>();
			Student student = null;
			String personId = null;
			for (int i = 1; i < data.size(); i++) {
				List<String> temp = data.get(i);
				student = new Student();
				student.setClassId(temp.get(0));
				student.setUserId(temp.get(1));
				student.setUserPwd(temp.get(1));
				student.setUserName(temp.get(2));
				student.setPersonId(temp.get(3));
				personId = student.getPersonId();
				student.setStuOrigin(this.getOrigin(personId));
				student.setStuBirthday(this.getBirthday(personId));
				student.setStuSex(this.getSex(personId));
				student.setStuHome(temp.get(4));
				System.out.println(student.toString());
				students.add(student);
			}
			studentDao.insert(students);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	private boolean checkTempleteFile(List<String> temp) {
		boolean flag = false;
		if ("班级编号".equals(temp.get(0)) && "学籍号".equals(temp.get(1)) && "姓名".equals(temp.get(2))&& "身份证号".equals(temp.get(3))&& "家庭地址".equals(temp.get(4))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 解析身份证中的性别
	 * 
	 * @param personId
	 * @return
	 */
	private int getSex(String personId) {
		Integer sex = Integer.valueOf(personId.charAt(17));
		return sex % 2;
	}

	/**
	 * 解析身份证中的生源地
	 * 
	 * @param personId
	 * @return
	 */
	private Integer getOrigin(String personId) {
		String string = personId.substring(0, 1) + "0000";
		Integer integer = new Integer(string);
		return integer;
	}

	/**
	 * 解析身份证中的生日
	 * 
	 * @param personId
	 * @return
	 */
	private String getBirthday(String personId) {
		String strDate = personId.substring(6, 10) + "-" + personId.substring(10, 12) + "-"
				+ personId.substring(12, 14);
		return strDate;

	}
	@Override
	public String getStudentsByPage(int pageSize, int pageIndex) throws IndexOutOfPageException {
		System.out.println("StudentServiceImpl.getStudentsByPage()");
		PageMethods<Student> pageMethods = null; // 实例页面操作对象
		List<Student> students = studentDao.select("true");
		int totalRows = students.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<Student> lists = studentDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	@Override
	public boolean insertStudent(Student student) {
		System.out.println("StudentServiceImpl.insertStudent()");
		List<Student> list = new ArrayList<>();
		list.add(student);
		return studentDao.insert(list);
	}

}
