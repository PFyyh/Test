package com.pesystem.entity;

/**
 * 学生
 * @author 一包辣条丶
 *
 */
public class Student extends User {
	
	private String classId;
	private String stuBirthday;
	private String stuHome;
	private Integer stuNation;
	private Integer stuOrigin;
	private Integer stuSex;
	private String personId;
	private String majorName;
	private String facultyName;
	public String getMajorName() {
		return majorName;
	}
 
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	

	@Override
	public String toString() {
		return "Student [classId=" + classId + ", stuBirthday=" + stuBirthday + ", stuHome=" + stuHome + ", stuNation="
				+ stuNation + ", stuOrigin=" + stuOrigin + ", stuSex=" + stuSex + ", personId=" + personId
				+ ", majorName=" + majorName + ", facultyName=" + facultyName + "]";
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public String getStuHome() {
		return stuHome;
	}

	public void setStuHome(String stuHome) {
		this.stuHome = stuHome;
	}

	public Integer getStuNation() {
		return stuNation;
	}

	public void setStuNation(Integer stuNation) {
		this.stuNation = stuNation;
	}

	public Integer getStuOrigin() {
		return stuOrigin;
	}

	public void setStuOrigin(Integer stuOrigin) {
		this.stuOrigin = stuOrigin;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	
}//end 学锟斤拷