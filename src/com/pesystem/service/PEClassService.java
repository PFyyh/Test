package com.pesystem.service;

import java.io.File;
import java.util.List;

import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Teacher;
import com.util.page.IndexOutOfPageException;

public interface PEClassService {
	/**
	 * 注册班级
	 * @param peClass
	 * @return
	 */
	boolean addPEClass(PEClass peClass);
	/**
	 * 增
	 * @param peclass
	 * @return
	 */
	public boolean addPEClass(List<PEClass> peclass);
	
	/**
	 *批量导入体育班级
	 * @param file
	 * @return
	 */
	boolean importPEClasses(File file);
	
	/**
	 * 设置管理班级的学生
	 * @param file
	 * @return
	 */
	boolean importStudents(File file);
	
	/**
	 * 更新体育班级信息
	 * @param peClass
	 * @return
	 */
	boolean updatePEClasses(PEClass peClass);
	
	/**
	 * 删除体育班级
	 * @param peClass
	 * @return
	 */
	boolean deletePEClass(PEClass peClass);
	
	/**
	 * 查询老师的管理班级
	 * @param teacher
	 * @return
	 */
	List<PEClass> selectPEClasses(Teacher teacher);
	
	/**
	 * 遍历所有
	 * @return
	 */
	List<PEClass> selectAll();
	
	/**
	 * 分页
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IndexOutOfPageException
	 */
	String getPEClassByPage(int pageSize,int pageIndex) throws IndexOutOfPageException;
}
