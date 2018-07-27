package com.pesystem.entity;


/**
 * 专业
 * @author 一包辣条丶
 *
 */
public class Major{

	private Integer facultyId;
	private Integer majorId;
	private String majorName;
	private String facultyName;
	public Major(int facultyId, int majorId, String majorName) {
		super();
		this.facultyId = facultyId;
		this.majorId = majorId;
		this.majorName = majorName;
	}
	public Major() {
		// TODO Auto-generated constructor stub
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	@Override
	public String toString() {
		return "Major [facultyId=" + facultyId + ", majorId=" + majorId + ", majorName=" + majorName + ", facultyName="
				+ facultyName + "]";
	}


}