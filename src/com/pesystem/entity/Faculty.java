package com.pesystem.entity;

/**
 * 学院
 * @author 一包辣条丶
 *
 */
public class Faculty{

	private Integer facultyId;
	private String facultyName;
	public Faculty() {
	}
	public Faculty(int facultyId, String facultyName) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
	}
	public Integer getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	

	
}//end 学院