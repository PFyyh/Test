package com.pesystem.service;

import com.pesystem.entity.Faculty;

import java.util.List;

public interface FacultyService {
	/**
	 * 遍历学院
	 * @return
	 */
	public List<Faculty> selectFaculties();
	
	/**
	 * 监测学院是否存在
	 * @param name
	 * @return
	 */
	public boolean checkExistFaculty(String name);
	
	/**
	 * 删除学院
	 * @param facultyId
	 * @return
	 */
	public boolean deleteFaculty(int facultyId);
	
	/**
	 * 注册学院
	 * @param name
	 * @return
	 */
	public boolean addFaculty(String name);
	
	/**
	 * 重命名，学院编号 和新名字。
	 * @param faculty
	 * @return
	 */
	public boolean renameFaculty(Faculty faculty);
	
	/**
	 * 添加学院
	 * @param faculties
	 * @return
	 */
	public boolean addFaculty(List<Faculty> faculties);
	

}
