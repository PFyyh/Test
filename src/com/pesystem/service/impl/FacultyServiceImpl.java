package com.pesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.FacultyDao;
import com.pesystem.dao.impl.FacultyDaoImpl;
import com.pesystem.entity.Faculty;
import com.pesystem.service.FacultyService;

public class FacultyServiceImpl implements FacultyService {

	private FacultyDao facultyDao = new FacultyDaoImpl();

	@Override
	public List<Faculty> selectFaculties() {
		List<Faculty> faculties = facultyDao.select("true");
		return faculties;
	}

	@Override
	public boolean checkExistFaculty(String name) {
		List<Faculty> faculties = facultyDao.select(" facultyName=\"" + name);
		boolean flag = false;
		if (faculties.size() == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteFaculty(int facultyId) {
		return facultyDao.delete(facultyId);
	}

	@Override
	public boolean addFaculty(List<Faculty> faculties) {
		return facultyDao.insert(faculties);
	}

	@Override
	public boolean addFaculty(String name) {
		System.out.println("FacultyServiceImpl.addFaculty()");
		List<Faculty> faculties = new ArrayList<>();	//生成list对象存入名字
		Faculty faculty= new Faculty();
		faculty.setFacultyName(name);
		faculties.add(faculty);
		boolean flag = this.addFaculty(faculties);
		System.out.println(flag);
		return flag;
	}

	@Override
	public boolean renameFaculty(Faculty faculty) {
		return facultyDao.update(faculty);
	}

}
