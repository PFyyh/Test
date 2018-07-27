package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Admin;

public interface AdminService {
	/**
	 * ���¹���Ա��Ϣ
	 * 
	 * @param admin
	 * @return
	 */
	boolean updateAdmin(Admin admin);
	
	/**
	 * ������Ա���
	 * @param admin
	 * @return
	 */
	boolean checkAdmin(Admin admin);
	
	/**
	 * ��ȡ����Ա���
	 * @return
	 */
	Admin getAdmin(Admin admin);
	
	/**
	 * ɾ������Ա���
	 * @return
	 */
	boolean deleteAdmin(Admin admin);
	
	/**
	 * ��������Ա
	 * @return
	 */
	List<Admin> selectAdmin(); 
	
	/**
	 * ���һ������Ա
	 * @param admin
	 * @return
	 */
	boolean insert(Admin admin);
	
	/**
	 * ���������Ա
	 * @param admins
	 * @return
	 */
	boolean insert(List<Admin> admins );
}
