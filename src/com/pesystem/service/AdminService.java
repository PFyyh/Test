package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Admin;

public interface AdminService {
	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 * @return
	 */
	boolean updateAdmin(Admin admin);
	
	/**
	 * 检查管理员身份
	 * @param admin
	 * @return
	 */
	boolean checkAdmin(Admin admin);
	
	/**
	 * 获取管理员身份
	 * @return
	 */
	Admin getAdmin(Admin admin);
	
	/**
	 * 删除管理员身份
	 * @return
	 */
	boolean deleteAdmin(Admin admin);
	
	/**
	 * 遍历管理员
	 * @return
	 */
	List<Admin> selectAdmin(); 
	
	/**
	 * 添加一个管理员
	 * @param admin
	 * @return
	 */
	boolean insert(Admin admin);
	
	/**
	 * 添加许多管理员
	 * @param admins
	 * @return
	 */
	boolean insert(List<Admin> admins );
}
