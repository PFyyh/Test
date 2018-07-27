package com.pesystem.service;

import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.Tester;
import com.util.page.IndexOutOfPageException;

public interface TesterService {
	/**
	 * 更新测试人员信息
	 * 
	 * @param Tester
	 * @return
	 */
	boolean updateTester(Tester tester);
	
	/**
	 * 检查测试人员身份
	 * @param Tester
	 * @return
	 */
	boolean checkTester(Tester tester);
	
	/**
	 * 获取测试人员身份
	 * @return
	 */
	Tester getTester(Tester tester);
	
	/**
	 * 删除测试人员身份
	 * @return
	 */
	boolean deleteTester(Tester tester);
	
	/**
	 * 遍历测试人员
	 * @return
	 */
	List<Tester> selectTester();
	
	/**
	 * 分页遍历
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	String getTestersByPage(int pageSize, int pageIndex) throws IndexOutOfPageException; 
	
	/**
	 * 注册测试人员
	 * @param tester
	 * @return
	 */
	boolean addTester(Tester tester);
	
	/**
	 * 遍历所有
	 * @return
	 */
	List<Tester> selectAll();
}
