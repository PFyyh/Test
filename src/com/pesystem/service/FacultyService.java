package com.pesystem.service;

import com.pesystem.entity.Faculty;

import java.util.List;

public interface FacultyService {
	/**
	 * ����ѧԺ
	 * @return
	 */
	public List<Faculty> selectFaculties();
	
	/**
	 * ���ѧԺ�Ƿ����
	 * @param name
	 * @return
	 */
	public boolean checkExistFaculty(String name);
	
	/**
	 * ɾ��ѧԺ
	 * @param facultyId
	 * @return
	 */
	public boolean deleteFaculty(int facultyId);
	
	/**
	 * ע��ѧԺ
	 * @param name
	 * @return
	 */
	public boolean addFaculty(String name);
	
	/**
	 * ��������ѧԺ��� �������֡�
	 * @param faculty
	 * @return
	 */
	public boolean renameFaculty(Faculty faculty);
	
	/**
	 * ���ѧԺ
	 * @param faculties
	 * @return
	 */
	public boolean addFaculty(List<Faculty> faculties);
	

}
