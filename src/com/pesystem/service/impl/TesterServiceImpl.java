package com.pesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.TesterDao;
import com.pesystem.dao.impl.TesterDaoImpl;
import com.pesystem.entity.Student;
import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.pesystem.service.TesterService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public class TesterServiceImpl implements TesterService {
	TesterDao testerDao = new TesterDaoImpl();

	@Override
	public boolean updateTester(Tester tester) {
		boolean flag = false;
		List<Tester> testers = testerDao.select(" testerId=\"" + tester.getUserId()+"\"");
		if (testers.size() == 1) {
			setUpdateTester(testers.get(0), tester);
			flag = testerDao.update(testers.get(0));
		}
		return flag;
	}

	@Override
	public boolean checkTester(Tester tester) {
		boolean flag = false;
		List<Tester> testers = testerDao.select(" testerId=\"" + tester.getUserId()+"\"");
		if (testers.get(0) != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Tester getTester(Tester tester) {
		if (tester==null) {
			return null;
		}
		List<Tester> testers = testerDao
				.select(" testerId=\"" + tester.getUserId() + "\" and testerPwd=md5(\"" + tester.getUserPwd()+"\")");
		if (testers.size()==0) {
			return null;
		}
		return testers.get(0);
	}

	@Override
	public boolean deleteTester(Tester tester) {
		return testerDao.delete(tester.getUserId());
	}

	@Override
	public List<Tester> selectTester() {
		return testerDao.select("true");
	}

	private static void setUpdateTester(Tester oldTester, Tester newTester) {
		if (newTester.getUserEmail() != null && !"".equals(newTester.getUserEmail())) {
			oldTester.setUserEmail(newTester.getUserEmail());
		}
		if (newTester.getUserName() != null && !"".equals(newTester.getUserName())) {
			oldTester.setUserName(newTester.getUserName());
		}
		if (newTester.getUserPwd() != null && !"".equals(newTester.getUserPwd())) {
			oldTester.setUserPwd(newTester.getUserPwd());
		}
		if (newTester.getUserTel() != null && !"".equals(newTester.getUserTel())) {
			oldTester.setUserTel(newTester.getUserTel());
		}
	}

	@Override
	public String getTestersByPage(int pageSize, int pageIndex) {
		PageMethods<Tester> pageMethods = null; // 实例页面操作对象
		List<Tester> students = testerDao.select("true");
		int totalRows = students.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<Tester> lists = testerDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	@Override
	public boolean addTester(Tester tester) {
		boolean flag = false;
		tester.setUserPwd(tester.getUserId());
		List<Tester> testers = new ArrayList<>();
		testers.add(tester);
		flag = testerDao.insert(testers);
		return flag;
	}

	@Override
	public List<Tester> selectAll() {
		return testerDao.select("true");
	}
}
