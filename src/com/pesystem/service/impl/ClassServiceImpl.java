package com.pesystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.ClassDao;
import com.pesystem.dao.MajorDao;
import com.pesystem.dao.impl.ClassDaoImpl;
import com.pesystem.dao.impl.MajorDaoImpl;
import com.pesystem.entity.Clazz;
import com.pesystem.entity.Major;
import com.pesystem.entity.Tester;
import com.pesystem.service.ClassService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.poi.method.ExcelAPI;

public class ClassServiceImpl implements ClassService {
	ClassDao clazzDao = new ClassDaoImpl();
	MajorDao majorDao = new MajorDaoImpl();

	@Override
	public boolean addClass(Clazz clazz) {
		List<Clazz> clazzs = new ArrayList<>();
		clazzs.add(clazz);
		return clazzDao.insert(clazzs);
	}

	@Override
	public boolean deleteClazz(String clazzId) {
		return clazzDao.delete(clazzId);
	}

	@Override
	public boolean updateClazz(Clazz clazz) {
		List<Clazz> clazzs = clazzDao.select(" classId=\"" + clazz.getClassId()+"\"");
		boolean flag = false;
		if (clazzs.size() == 1) {
			setUpdateClazz(clazzs.get(0), clazz);
			flag = clazzDao.update(clazzs.get(0));
		}
		return flag;
	}

	private static void setUpdateClazz(Clazz oldClazz, Clazz newClazz) {
		if (newClazz.getClassName() != null && !"".equals(newClazz.getClassName())) {
			oldClazz.setClassName(newClazz.getClassName());
		}
		if (newClazz.getClassYear() != 0) {
			oldClazz.setClassYear(newClazz.getClassYear());
		}
		if (newClazz.getMajorId() != 0) {
			oldClazz.setMajorId(newClazz.getMajorId());
		}
		if (newClazz.getTesterId() != null && !"".equals(newClazz.getTesterId())) {
			oldClazz.setTesterId(newClazz.getTesterId());
		}
	}

	@Override
	public String geClassesByPage(int pageSize, int pageIndex) throws IndexOutOfPageException {
		PageMethods<Clazz> pageMethods = null; // ʵ��ҳ���������
		List<Clazz> students = clazzDao.select("true");
		int totalRows = students.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<Clazz> lists = clazzDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "�ɹ�").toString();
	}

	@Override
	public List<Clazz> selectClasses(Tester tester) {
		return clazzDao.select("testerId=" + tester.getUserId());
	}

	@Override
	public boolean addClass(List<Clazz> clazz) {
		return clazzDao.insert(clazz);
	}

	@Override
	public boolean importClasses(File file) {
		file = new File("E:/professional/��������/���ģ��/importClassesTemplete.xlsx");
		boolean flag = false;
		try {
			ExcelAPI excelApi = new ExcelAPI(file);
			excelApi.openExcel();
			List<List<String>> data = excelApi.getData();
			List<Clazz> clazzs = new ArrayList<>();
			Clazz clazz;
			// ����ĵ��Ƿ��������༶
			if (!checkTempleteFile(data.get(0))) {
				return flag;
			}
			Integer year;
			int majorId;
			for (int i = 1; i < data.size(); i++) {
				clazz = new Clazz();
				List<String> temp = data.get(i);
				year = new Integer(temp.get(0));
				clazz.setClassYear(year);//���ü�
				clazz.setClassName(temp.get(3));//���ð༶����
				majorId = getMajorId(temp.get(1)); // ��ѧԺ���滻Ϊרҵ���
				System.out.println("temp:"+majorId);

				if (majorId == 0) {
					return false;
				}
				clazz.setMajorId(majorId);
				clazz.setClassId(temp.get(2));
				clazzs.add(clazz);
			}
			flag = this.addClass(clazzs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean setTester(Clazz clazz) {
		return false;
	}

	/**
	 * ����ļ��Ƿ�Ϸ�
	 * 
	 * @param temp
	 * @return
	 */
	private boolean checkTempleteFile(List<String> temp) {
		boolean flag = false;
		if ("��".equals(temp.get(0)) && "����רҵ����".equals(temp.get(1)) && "�༶���".equals(temp.get(2))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * ��רҵ�����滻Ϊרҵ���
	 * 
	 * @param majorName
	 * @return
	 */
	private Integer getMajorId(String majorName) {
		Integer num = null;
		if (majorName == null || "".equals(majorName)) {
			System.out.println("��");
			return num;
		}
		List<Major> majors = majorDao.select("true");
		for (Major major : majors) {
			if (majorName.equals(major.getMajorName())) {
				num = major.getMajorId();
				break;
			}
		}
		
		System.out.println("");
		return num;
	}

	@Override
	public List<Clazz> selectAll() {
		return clazzDao.select("true");
	}
}
