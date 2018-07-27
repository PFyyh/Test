package com.pesystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.PEClassDao;
import com.pesystem.dao.SCDao;
import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.SC;
import com.pesystem.entity.Student;
import com.pesystem.entity.Teacher;
import com.pesystem.service.PEClassService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.poi.method.ExcelAPI;
import com.yyh.util.MyTime;
import com.pesystem.dao.impl.PEClassDaoImpl;
import com.pesystem.dao.impl.SCDaoImpl;;

public class PEClassServiceImpl implements PEClassService {
	PEClassDao peClassDao = new PEClassDaoImpl();

	@Override
	public boolean addPEClass(PEClass peClass) {
		List<PEClass> peClasses = new ArrayList<>();
		peClasses.add(peClass);
		return peClassDao.insert(peClasses);
	}

	@Override
	public boolean importPEClasses(File file) {
		boolean flag = false;
		try {
			ExcelAPI excelApi = new ExcelAPI(file);
			excelApi.openExcel();
			List<List<String>> data = excelApi.getData();
			for (List<String> row : data) {
				for (String string : row) {
					System.out.print(string + "->");
				}
				System.out.println();
			}
			List<PEClass> peClasses = new ArrayList<>();
			PEClass peClass;
			for (int i = 1; i < data.size(); i++) {
				peClass = new PEClass();
				List<String> temp = data.get(i);
				peClass.setClassId(temp.get(0));
				peClass.setClassName(temp.get(0));
				peClass.setTesterId(temp.get(1));
				peClass.setClassName(temp.get(3));
				peClass.setClassYear(MyTime.getCurrectYear());
				peClasses.add(peClass);
			}
			flag = this.addPEClass(peClasses);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatePEClasses(PEClass peClass) {
		List<PEClass> peClasses = peClassDao.select(" peclassid=" + peClass.getClassId());
		boolean flag = false;
		if (peClasses.size() == 1) {
			setUpdatePEClass(peClasses.get(0), peClass);
			flag = peClassDao.update(peClasses.get(0));
		}
		return flag;
	}

	/**
	 * 可以更新的数据包括：班级名称，测试人员编号，年份不可修改
	 * 
	 * @param oldPEClass
	 * @param newPEClass
	 */
	private void setUpdatePEClass(PEClass oldPEClass, PEClass newPEClass) {
		if (newPEClass.getClassName() != null && !"".equals(newPEClass.getClassName())) {
			oldPEClass.setClassName(newPEClass.getClassName());
		}
		if (newPEClass.getTesterId() != null && !"".equals(newPEClass.getTesterId())) {
			oldPEClass.setTesterId(newPEClass.getTesterId());
		}
	}

	@Override
	public boolean deletePEClass(PEClass peClass) {
		return peClassDao.delete(peClass.getClassId());
	}

	@Override
	public List<PEClass> selectPEClasses(Teacher teacher) {
		return peClassDao.select(" teacherId=" + teacher.getUserId());
	}

	@Override
	public boolean addPEClass(List<PEClass> peclass) {
		return peClassDao.insert(peclass);
	}

	@Override
	public boolean importStudents(File file) {
		boolean flag = false;
		try {
			ExcelAPI excelApi = new ExcelAPI(file);
			excelApi.openExcel();
			List<List<String>> data = excelApi.getData();
			// 检查文档是否是行政班级
			if (!checkTempleteFile(data.get(0))) {
				return flag;
			}
			List<SC> scs = new ArrayList<>();
			SC sc;
			for (int i = 1; i < data.size(); i++) {
				sc = new SC();
				List<String> temp = data.get(i);
				sc.setPEClassId(temp.get(0));
				sc.setStuNo(temp.get(2));
				scs.add(sc);
			}
			flag = this.addStudents(scs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	private boolean checkTempleteFile(List<String> temp) {
		boolean flag = false;
		if ("班级编号".equals(temp.get(0)) && "老师编号".equals(temp.get(1)) && "学号".equals(temp.get(2))&& "班级名称".equals(temp.get(3))) {
			flag = true;
		}
		return flag;
	}

	private boolean addStudents(List<SC> scs){
		SCDao scDao = new SCDaoImpl();
		boolean flag = false;
		scDao.insert(scs);
		return flag ;
	}

	@Override
	public List<PEClass> selectAll() {
		return peClassDao.select("true");
	}

	@Override
	public String getPEClassByPage(int pageSize, int pageIndex) throws IndexOutOfPageException {
		System.out.println("StudentServiceImpl.getStudentsByPage()");
		PageMethods<PEClass> pageMethods = null; // 实例页面操作对象
		List<PEClass> students = peClassDao.select("true");
		int totalRows = students.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<PEClass> lists = peClassDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}
	
	
}
