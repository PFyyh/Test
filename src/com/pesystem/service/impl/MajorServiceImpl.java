package com.pesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pesystem.dao.MajorDao;
import com.pesystem.dao.impl.MajorDaoImpl;
import com.pesystem.entity.Major;
import com.pesystem.service.MajorService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;

public class MajorServiceImpl implements MajorService {
	MajorDao majorDao = new MajorDaoImpl();
	
	@Override
	public boolean updateMajor(Major major){
		boolean flag = false;
		if (major.getMajorId()==0) {
			return flag;
		}
		List<Major> majors = majorDao.select(" majorId = "+major.getMajorId());
		if (majors.size()==1) {
			setUpdateMajor(majors.get(0), major);
			System.out.println("majorId(0):"+majors.get(0));
			flag = majorDao.update(majors.get(0));
		}
		return flag;
		
	}
	
	/**
	 * 外界可修改的信息
	 * @param oldMajor
	 * @param newMajor
	 */
	private static void setUpdateMajor(Major oldMajor,Major newMajor){
		if (newMajor.getMajorName()!=null||"".equals(newMajor.getMajorName())) {
			oldMajor.setMajorName(newMajor.getMajorName());
		}
		if (newMajor.getFacultyId()!=0) {
			oldMajor.setFacultyId(newMajor.getFacultyId());
		}
		System.out.println(oldMajor+" "+newMajor);
	}

	@Override
	public boolean addMajor(Major major) {
		List<Major> majors = new ArrayList<>();
		majors.add(major);
		return majorDao.insert(majors);
	}

	@Override
	public boolean addMajor(List<Major> majors) {
		return majorDao.insert(majors);
	}

	@Override
	public boolean deleteMajor(int majorId) {
		return majorDao.delete(majorId);
	}
	
	@Override
	public List<Major> selectMajors() {
		return majorDao.select("true");
	}

	@Override
	public String getMajorsByPage(PageMethods<Major> pageMethods) throws IndexOutOfPageException {
		int searchPageIndex = pageMethods.getPageIndex();
		int pageSize = pageMethods.getPageSize();
		List<Major> lists = majorDao.select(" true limit " + (searchPageIndex-1)*pageSize+","+pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}
	
	@Override
	public int getCount(){
		List<Major> majors = this.selectMajors();
		return majors.size();
	}
}
