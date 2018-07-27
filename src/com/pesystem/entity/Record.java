package com.pesystem.entity;


/**
 * 记录
 * @author 一包辣条丶
 *
 */
public class Record {

	private String thousandRun;
	private String fivetyRun;
	private Integer vitalcapacity;
	private Double standingBroadJump;
	private Integer year;
	private Double height;
	private Double beginToBend;
	private Double weight;
	private String stuNo;
	private Integer situp;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getThousandRun() {
		return thousandRun;
	}
	public void setThousandRun(String thousandRun) {
		this.thousandRun = thousandRun;
	}
	public String getFivetyRun() {
		return fivetyRun;
	}
	public void setFivetyRun(String fivetyRun) {
		this.fivetyRun = fivetyRun;
	}
	public Integer getVitalcapacity() {
		return vitalcapacity;
	}
	public void setVitalcapacity(Integer vitalcapacity) {
		this.vitalcapacity = vitalcapacity;
	}
	public Double getStandingBroadJump() {
		return standingBroadJump;
	}
	public void setStandingBroadJump(Double standingBroadJump) {
		this.standingBroadJump = standingBroadJump;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getBeginToBend() {
		return beginToBend;
	}
	public void setBeginToBend(Double beginToBend) {
		this.beginToBend = beginToBend;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getStuNo() {
		return stuNo;
	}
	@Override
	public String toString() {
		return "Record [thousandRun=" + thousandRun + ", fivetyRun=" + fivetyRun + ", vitalcapacity=" + vitalcapacity
				+ ", standingBroadJump=" + standingBroadJump + ", year=" + year + ", height=" + height
				+ ", beginToBend=" + beginToBend + ", weight=" + weight + ", stuNo=" + stuNo + ", situp=" + situp + "]";
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public Integer getSitup() {
		return situp;
	}
	public void setSitup(Integer situp) {
		this.situp = situp;
	}

}