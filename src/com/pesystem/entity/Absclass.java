package com.pesystem.entity;


/**
 * 抽象类
 * @author 一包辣条丶
 *
 */
public abstract class Absclass{

	protected String classId;
	protected String className;
	protected int classYear;
	protected String testerId;
	protected String testerName;
	
	public String getTesterName() {
		return testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public Absclass(){

	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}
	

}//end 锟洁级