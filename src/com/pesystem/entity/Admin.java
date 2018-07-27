package com.pesystem.entity;

/**
 * @author Administrator
 * @version 1.0
 * @created 29-����-2018 8:34:24
 */
public class Admin extends User{

	public Admin(){
		super();
	}
	public Admin(String userId,String userName,String userTel,String userEmail,String userPwd){
		super(userEmail, userId, userName, userPwd, userTel);
	}
	@Override
	public String toString() {
		return "Admin [userEmail=" + userEmail + ", userId=" + userId + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userTel=" + userTel + ", getUserEmail()=" + getUserEmail() + ", getUserId()="
				+ getUserId() + ", getUserName()=" + getUserName() + ", getUserPwd()=" + getUserPwd()
				+ ", getUserTel()=" + getUserTel() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}