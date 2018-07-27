package com.pesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.AdminDao;
import com.pesystem.dao.impl.AdminDaoImpl;
import com.pesystem.entity.Admin;
import com.pesystem.service.AdminService;

public class AdminServiceImpl implements AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	@Override
	public boolean updateAdmin(Admin admin) {
		boolean flag = false;
		List<Admin> admins = adminDao.select(" adminId=\"" + admin.getUserId()+"\"");
		if (admins.size() == 1) {
			setUpdateAdmin(admins.get(0), admin);
			flag = adminDao.update(admins.get(0));
		}
		return flag;
	}

	/**
	 * 可修改的数据包括email 用户名 密码 和电话号码
	 * 
	 * @param oldAdmin
	 * @param newAdmin
	 */
	private static void setUpdateAdmin(Admin oldAdmin, Admin newAdmin) {
		if (newAdmin.getUserEmail() != null && !"".equals(newAdmin.getUserEmail())) {
			oldAdmin.setUserEmail(newAdmin.getUserEmail());
		}
		if (newAdmin.getUserName() != null && !"".equals(newAdmin.getUserName())) {
			oldAdmin.setUserName(newAdmin.getUserName());
		}
		if (newAdmin.getUserPwd() != null && !"".equals(newAdmin.getUserPwd())) {
			oldAdmin.setUserPwd(newAdmin.getUserPwd());
		}
		if (newAdmin.getUserTel() != null && !"".equals(newAdmin.getUserTel())) {
			oldAdmin.setUserTel(newAdmin.getUserTel());
		}
	}

	@Override
	public boolean checkAdmin(Admin admin) {
		List<Admin> admins = adminDao
				.select(" adminId =" + admin.getUserId() + " and adminPwd = md5(\"" + admin.getUserPwd() + "\")");
		boolean flag = false;
		if (admins.size() == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Admin getAdmin(Admin admin) {
		if (admin == null) {
			return null;
		}

		List<Admin> admins = adminDao
				.select(" adminId =\"" + admin.getUserId() + "\" and adminPwd = md5(\"" + admin.getUserPwd() + "\")");
		if (admins.size()==0) {
			return null;
		}
		return admins.get(0);
	}

	@Override
	public boolean deleteAdmin(Admin admin) {
		return adminDao.delete(admin.getUserId());
	}

	@Override
	public List<Admin> selectAdmin() {
		return adminDao.select("true");
	}

	@Override
	public boolean insert(Admin admin) {
		boolean flag = false;
		if (admin==null) {
			return flag;
		}
		List<Admin> admins = new ArrayList<>();
		admins.add(admin);
		return this.insert(admins);
	}

	@Override
	public boolean insert(List<Admin> admins) {
		for(Admin admin:admins){
			admin.setUserPwd(admin.getUserId());
		}
		return adminDao.insert(admins);
	}
}
