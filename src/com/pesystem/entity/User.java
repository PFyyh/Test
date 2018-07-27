package com.pesystem.entity;


/**
 * 用户
 * @author 一包辣条丶
 *
 */
public class User {

	protected String userEmail;
	protected String userId;
	protected String userName;
	protected String userPwd;
	protected String userTel;
	
	
	public User(String userEmail, String userId, String userName, String userPwd, String userTel) {
		super();
		this.userEmail = userEmail;
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userTel = userTel;
	}

	public User(){

	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd
				+ ", userTel=" + userTel + "]";
	}
	
}