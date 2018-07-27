
package com.pesystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.entity.Record;
import com.pesystem.service.RecordService;
import com.pesystem.service.impl.RecordServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.upload.FileDispose;
import com.yyh.MyUtil.MyString;

/**
 * Servlet implementation class RecordController
 */
public class RecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RecordService recordService = new RecordServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recordService.importRecords(new File("213"));
		String method = request.getParameter("method");
		if ("getRecordAll".equals(method)) {
			searchPage(request, response);
		} else if ("addRecord".equals(method)) {
			addRecord(request, response);
		} else if ("delRecord".equals(method)) {
			delRecord(request, response);
		} else if ("updateRecord".equals(method)) {
			updateRecord(request, response);
		} else if ("uploadRecordAll".equals(method)) {
			uploadRecordAll(request, response);
		} else {
			response.sendError(401);
		}
	}


	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @Title: uploadRecordAll @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void uploadRecordAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileDispose fileDispose = new FileDispose("xls","xlsx");
		fileDispose.upLoadFile(request, response);
		File file = fileDispose.getFirstFile();
		File xlsx = new File(
				request.getSession().getServletContext().getRealPath("") + File.separatorChar + file.getPath());
		boolean flag[] = recordService.importRecords(xlsx); 
		boolean result = true;
		int code = 0;
		String mString = "";
        for (boolean b : flag) {
			if (b==false) {
				result = false;
			}
		}
        if (result==false) {
			code = 1;
		}
		PrintWriter pw = response.getWriter();
		pw.write(fileDispose.responseToJSON(code, mString));
	}

	/**
	 * @throws IOException @Title: updateClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void updateRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("开始修改");
		String stuNo = request.getParameter("stuNo");
		String year = request.getParameter("year");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String vitalCapacity = request.getParameter("vitalCapacity");
		String beginToBend = request.getParameter("beginToBend");
		String standingBroadJump = request.getParameter("standingBroadJump");
		String sitUp = request.getParameter("sitUp");
		String fivetyRun = request.getParameter("fivetyRun");
		String thousandRun = request.getParameter("thousandRun");
		boolean flag = false;
		if (MyString.isEmpty(stuNo,year)) {
		}else{
			Record record = new Record();
			Integer integerTemp = new Integer(year);
			record.setStuNo(stuNo);
			record.setYear(integerTemp);
			Double doubleTemp = new Double(beginToBend);
			record.setBeginToBend(doubleTemp);
			record.setFivetyRun(fivetyRun);
			doubleTemp = new Double(height);
			record.setHeight(doubleTemp);
			integerTemp = new Integer(sitUp);
			record.setSitup(integerTemp);
			doubleTemp = new Double(standingBroadJump);
			record.setStandingBroadJump(doubleTemp);
			record.setThousandRun(thousandRun);
			integerTemp = new Integer(vitalCapacity);
			record.setVitalcapacity(integerTemp);
			doubleTemp = new Double(weight);
			record.setWeight(doubleTemp);
			flag = recordService.inputRecord(record);
		}
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	/**
	 * @throws IOException @Title: delClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void delRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String stuNo = request.getParameter("stuNo");
		String year = request.getParameter("year");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (MyString.isEmpty(stuNo,year)) {
		}else{
			flag = recordService.deleteRecords(stuNo, year);
		}
		out.println(flag);
		
	}

	/**
	 * @throws IOException @Title: addClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void addRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始添加");
		String stuNo = request.getParameter("stuNo");
		String year = request.getParameter("year");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String vitalCapacity = request.getParameter("vitalCapacity");
		String beginToBend = request.getParameter("beginToBend");
		String standingBroadJump = request.getParameter("standingBroadJump");
		String sitUp = request.getParameter("sitUp");
		String fivetyRun = request.getParameter("fivetyRun");
		String thousandRun = request.getParameter("thousandRun");
		
		boolean flag = false;
		if (MyString.isEmpty(stuNo,year)) {
		}else{
			Record record = new Record();
			Integer integerTemp = new Integer(year);
			record.setStuNo(stuNo);
			record.setYear(integerTemp);
			Double doubleTemp = new Double(beginToBend);
			record.setBeginToBend(doubleTemp);
			record.setFivetyRun(fivetyRun);
			doubleTemp = new Double(height);
			record.setHeight(doubleTemp);
			integerTemp = new Integer(sitUp);
			record.setSitup(integerTemp);
			doubleTemp = new Double(standingBroadJump);
			record.setStandingBroadJump(doubleTemp);
			record.setThousandRun(thousandRun);
			integerTemp = new Integer(vitalCapacity);
			record.setVitalcapacity(integerTemp);
			doubleTemp = new Double(weight);
			record.setWeight(doubleTemp);
			flag = recordService.inputRecord(record);
		}
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	/**
	 * @throws IOException @Title: getClassAll @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void getRecordAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String result = "{\r\n" + "	\"code\": 0,\r\n" + "	\"msg\": \"\",\r\n" + "	\"count\": 12,\r\n"
				+ "	\"data\": [\r\n" + "		{\r\n" + "			\"stuNo\": \"1\",\r\n"
				+ "			\"stuName\":\"学生\",\r\n" + "			\"year\": \"2018\",\r\n"
				+ "			\"height\": \"173\",\r\n" + "			\"weight\": \"50\",\r\n"
				+ "			\"vitalCapacity\": \"3800\",\r\n" + "			\"beginToBend\": \"15.9\",\r\n"
				+ "			\"standingBroadJump\": \"240\",\r\n" + "			\"sitUp\": \"20\",\r\n"
				+ "			\"fivetyRun\": \"7.3\",\r\n" + "			\"thousandRun\": \"3'66\"\r\n" + "		},\r\n"
				+ "		{\r\n" + "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "		,\r\n" + "		{\r\n"
				+ "			\"stuNo\": \"1\",\r\n" + "			\"stuName\":\"学生\",\r\n"
				+ "			\"year\": \"2018\",\r\n" + "			\"height\": \"173\",\r\n"
				+ "			\"weight\": \"50\",\r\n" + "			\"vitalCapacity\": \"3800\",\r\n"
				+ "			\"beginToBend\": \"15.9\",\r\n" + "			\"standingBroadJump\": \"240\",\r\n"
				+ "			\"sitUp\": \"20\",\r\n" + "			\"fivetyRun\": \"7.3\",\r\n"
				+ "			\"thousandRun\": \"3'66\"\r\n" + "		}\r\n" + "	]\r\n" + "}";
		PrintWriter out = response.getWriter();
		new String(result.getBytes(), "utf-8");
		System.out.println(result);
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void searchPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strPageSize = request.getParameter("limit"); // 获取每页条数
		String strPageIndex = request.getParameter("page"); // 请求页面
		if (strPageIndex == null || "".equals(strPageIndex) || strPageIndex == null || "".equals(strPageIndex)) {
			response.sendError(400);
		}
		int totalRows = recordService.count(); // 求结果集
		PrintWriter printWriter = response.getWriter();
		String str = null;
		PageMethods<Record> pageMethods = null; // 实例页面操作对象
		int pageIndex = Integer.parseInt(strPageIndex);
		int pageSize = Integer.parseInt(strPageSize);
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			response.sendError(400);
		}
		try {
			str = recordService.getRecordByPage(pageMethods);
		} catch (IndexOutOfPageException e) {
			pageMethods.toJSON(200, "数据异常");
		}
		printWriter.print(str);
	}

}
