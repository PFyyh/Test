package com.pesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.TeacherDao;
import com.pesystem.dao.impl.TeacherDaoImpl;
import com.pesystem.entity.Teacher;
import com.pesystem.service.TeacherService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public class TeacherServiceImpl implements TeacherService {
	TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public boolean updateTeacher(Teacher teacher) {
		System.out.println("TeacherServiceImpl.updateTeacher()");
		boolean flag = false;
		List<Teacher> students = teacherDao.select(" teacherId=\"" + teacher.getUserId() + "\"");
		if (students.size() == 1) {
			setUpdateTeacher(students.get(0), teacher);
			flag = teacherDao.update(teacher);
		}
		return flag;
	}

	private static void setUpdateTeacher(Teacher oldTeacher, Teacher newTeacher) {
		if (newTeacher.getUserEmail() != null && !"".equals(newTeacher.getUserEmail())) {
			oldTeacher.setUserEmail(newTeacher.getUserEmail());
		}
		if (newTeacher.getUserName() != null && !"".equals(newTeacher.getUserName())) {
			oldTeacher.setUserName(newTeacher.getUserName());
		}
		if (newTeacher.getUserPwd() != null && !"".equals(newTeacher.getUserPwd())) {
			oldTeacher.setUserPwd(newTeacher.getUserPwd());
		}
		if (newTeacher.getUserTel() != null && !"".equals(newTeacher.getUserTel())) {
			oldTeacher.setUserTel(newTeacher.getUserTel());
		}
	}

	@Override
	public boolean checkTeacher(Teacher teacher) {
		return false;
	}

	@Override
	public Teacher getTeacher(Teacher teacher) {
		if (teacher == null) {
			return null;
		}
		List<Teacher> teachers = teacherDao
				.select(" teacherId=\"" + teacher.getUserId() + "\"teacherPwd=\"" + teacher.getUserPwd() + "\"");
		if (teachers.size() == 0) {
			return null;
		}
		return teachers.get(0);
	}

	@Override
	public boolean deleteTeacher(Teacher teacher) {
		return teacherDao.delete(teacher.getUserId());
	}

	@Override
	public List<Teacher> selectTeacher() {
		return teacherDao.select("true");
	}

	@Override
	public String getTeachersByPage(int pageSize, int pageIndex) throws IndexOutOfPageException {
		PageMethods<Teacher> pageMethods = null; // 实例页面操作对象
		List<Teacher> teachers = teacherDao.select("true");
		int totalRows = teachers.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<Teacher> lists = teacherDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		boolean flag = false;
		teacher.setUserPwd(teacher.getUserId());
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher);
		flag = teacherDao.insert(teachers);
		return flag;
	}

	@Override
	public List<Teacher> selectAll() {
		return teacherDao.select("true");
	}
}
