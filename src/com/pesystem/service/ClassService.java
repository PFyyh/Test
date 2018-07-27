package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface ClassService {
	/**
	 * 注册班级
	 * 
	 * @param clazz
	 * @return
	 */
	boolean addClass(Clazz clazz);

	/**
	 * 注册班级
	 * 
	 * @param clazz
	 * @return
	 */
	boolean addClass(List<Clazz> clazz);

	/**
	 * 删除班级
	 * 
	 * @param clazzId
	 * @return
	 */
	boolean deleteClazz(String clazzId);

	/**
	 * 更新班级
	 * 
	 * @param clazz
	 * @return
	 */
	boolean updateClazz(Clazz clazz);

	/**
	 * 分页遍历班级
	 * 
	 * @param pageMethods
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String geClassesByPage(int pageSize, int pageIndex) throws IndexOutOfPageException;

	/**
	 * 通过测试人员编号获取到测试班级
	 * 
	 * @param tester
	 * @return
	 */
	List<Clazz> selectClasses(Tester tester);

	/**
	 * 批量导入体育班级
	 * 
	 * @param file
	 * @return
	 */
	boolean importClasses(File file);

	/**
	 * 管理员管理某个班级
	 * @param clazz
	 * @return
	 */
	boolean setTester(Clazz clazz);
	
	/**
	 * 遍历所有
	 * @return
	 */
	List<Clazz> selectAll();
}
