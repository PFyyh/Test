package com.pesystem.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.pesystem.dao.RecordDao;
import com.pesystem.dao.impl.ReordDaoImpl;
import com.pesystem.entity.Clazz;
import com.pesystem.entity.PEClass;
import com.pesystem.entity.Record;
import com.pesystem.entity.Student;
import com.pesystem.service.RecordService;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.poi.method.ExcelAPI;
import com.yyh.util.MyTime;

public class RecordServiceImpl implements RecordService {
	RecordDao recordDao = new ReordDaoImpl();

	@Override
	public boolean[] inputRecord(List<Record> records) {
		boolean[] flagArr = new boolean[records.size()];// 成功标识和失败
		for (int i = 0; i < records.size(); i++) {
			List<Record> recordTemp = recordDao
					.select(" stuNo=\"" + records.get(i).getStuNo() + "\" and year = " + records.get(i).getYear());
			if (recordTemp.size() == 1) {
				flagArr[i] = true;
			}
			if (flagArr[i]) {
				setUpdateRecord(recordTemp.get(0), records.get(i));
				flagArr[i] = this.update(recordTemp.get(0));
			} else {
				flagArr[i] = this.insert(records.get(i));
			}
		}
		return flagArr;
	}

	/**
	 * 将修改后的数据和未修改的数据项整合在一起。
	 * @param oldRecord
	 * @param newRecord
	 */
	private static void setUpdateRecord(Record oldRecord, Record newRecord) {
		if (newRecord.getBeginToBend() != null) {
			oldRecord.setBeginToBend(newRecord.getBeginToBend());
		}
		if (newRecord.getFivetyRun() != null) {
			oldRecord.setFivetyRun(newRecord.getFivetyRun());
		}
		if (newRecord.getHeight() != null) {
			oldRecord.setHeight(newRecord.getHeight());
		}
		if (newRecord.getSitup() != null) {
			oldRecord.setSitup(newRecord.getSitup());
		}
		if (newRecord.getStandingBroadJump() != null) {
			oldRecord.setStandingBroadJump(newRecord.getStandingBroadJump());
		}
		if (newRecord.getThousandRun() != null) {
			oldRecord.setThousandRun(newRecord.getThousandRun());
		}
		if (newRecord.getWeight() != null) {
			oldRecord.setWeight(newRecord.getWeight());
		}
		if(newRecord.getVitalcapacity()!=null){
			oldRecord.setVitalcapacity(newRecord.getVitalcapacity());
		}
	}

	/**
	 * 添加记录w
	 * 
	 * @param records
	 * @return
	 */
	private boolean insert(List<Record> records) {
		for (Record record : records) {
			if (!checkPK(record)) {
				return false;
			}
		}
		return recordDao.insert(records);
	}

	/**
	 * 添加记录
	 * 
	 * @param record
	 * @return
	 */
	private boolean insert(Record record) {
		List<Record> result = new ArrayList<>();
		result.add(record);
		return this.insert(result);

	}

	/**
	 * 检查主键是否合法
	 * 
	 * @param record
	 * @return
	 */
	private boolean checkPK(Record record) {
		if (record.getStuNo() == null || "".equals(record.getStuNo())) {
			return false;
		}
		if (record.getYear() == null || "".equals(record.getYear())) {
			Calendar date = Calendar.getInstance();
			Integer year = Integer.valueOf(date.get(Calendar.YEAR));
			record.setYear(year);
		}
		return true;
	}

	/**
	 * 更新记录
	 * 
	 * @param record
	 * @return
	 */
	private boolean update(Record record) {
		if (!checkPK(record)) {
			return false;
		}
		return recordDao.update(record);
	}

	/**
	 * 分页显示
	 */
	@Override
	public String selectRecords(PageMethods<Record> pageMethods) throws IndexOutOfPageException {
		int searchPageIndex = pageMethods.getPageIndex();
		int pageSize = pageMethods.getPageSize();
		List<Record> lists = recordDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	/**
	 * 测试人员获取测试班级班级
	 */
	@Override
	public List<Record> getTestStudentByClasses(Clazz clazz) {
		int year = MyTime.getCurrectYear();
		clazz.setClassYear(year);
		return recordDao.selectByClass(clazz);
	}
	
	@Override
	public List<Record> getTestStudentByPEClasses(PEClass peClass) {
		int year = MyTime.getCurrectYear();
		peClass.setClassYear(year);
		return recordDao.selectByPEClass(peClass);
	}

	@Override
	public String getRecordByPage(PageMethods<Record> pageMethods) throws IndexOutOfPageException {
		int searchPageIndex = pageMethods.getPageIndex();
		int pageSize = pageMethods.getPageSize();
		List<Record> lists = recordDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	@Override
	public int count() {
		List<Record> records = recordDao.select("true");
		return records.size();
	}
	
	/**
	 * 检查文档格式
	 * @param temp
	 * @return
	 */
	private boolean checkTempleteFile(List<String> temp) {
		boolean flag = false;
		if ("班级编号".equals(temp.get(0)) && "老师编号".equals(temp.get(1)) && "学号".equals(temp.get(2))&& "班级名称".equals(temp.get(3))) {
			flag = true;
		}
		return flag;
	}
	
	
	@Override
	public boolean[] importRecords(File file) {
		boolean[] flag = null;
		try {
			ExcelAPI excelApi = new ExcelAPI(file);
			excelApi.openExcel();
			List<List<String>> data = excelApi.getData();
			if (!checkTempleteFile(data.get(0))) {
				return flag;
			}
			flag = new boolean[data.size()];
			for (List<String> row : data) {
				for (String string : row) {
					System.out.print(string + "->");
				}
				System.out.println();
			}
			List<Record> records = new ArrayList<>();
			Record record = null;
			Double bDouble;
			Integer iInteger;
			for (int i = 1; i < data.size(); i++) {
				List<String> temp = data.get(i);
				record = new Record();
				record.setStuNo(temp.get(0));
				record.setYear(MyTime.getCurrectYear());
				bDouble = new Double(temp.get(1));
				record.setHeight(bDouble);
				bDouble = new Double(temp.get(2));
				record.setWeight(bDouble);
				iInteger = new Integer(temp.get(3));
				record.setSitup(iInteger);
				iInteger = new Integer(temp.get(4));
				record.setVitalcapacity(iInteger);
				System.out.println(record);
				records.add(record);
			}
			flag = this.inputRecord(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String getRecordsByPage(int pageSize, int pageIndex) throws IndexOutOfPageException {
		System.out.println("StudentServiceImpl.getStudentsByPage()");
		PageMethods<Record> pageMethods = null; // 实例页面操作对象
		List<Record> students = recordDao.select("true");
		int totalRows = students.size();
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			System.out.println(e1);
		}
		int searchPageIndex = pageMethods.getPageIndex();
		List<Record> lists = recordDao.select(" true limit " + (searchPageIndex - 1) * pageSize + "," + pageSize);
		pageMethods.setPageData(lists);
		return pageMethods.toJSON(0, "成功").toString();
	}

	@Override
	public boolean inputRecord(Record record) {
		List<Record> records = new ArrayList<>();
		records.add(record);
		boolean[] flag =  inputRecord(records);
		return flag[0];
	}

	@Override
	public boolean deleteRecords(String stuNo, String year) {
		Record record = new Record();
		record.setStuNo(stuNo);
		Integer integer = new Integer(year);
		record.setYear(integer);
		return recordDao.delete(record);
	}

}
