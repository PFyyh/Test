package com.pesystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.entity.Major;
import com.pesystem.service.MajorService;
import com.pesystem.service.impl.MajorServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class MajorController
 */
public class MajorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MajorService majorService = new MajorServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println("method:" + method);
		 if ("getMajorAll".equals(method)) {
			getMajorAll(request, response);
		} else if ("addMajor".equals(method)) {
			addMajor(request, response);
		} else if ("delMajor".equals(method)) {
			delMajor(request, response);
		} else if ("updateMajor".equals(method)) {
			updateMajor(request, response);
		}else if ("selectMajorAll".equals(method)) {
			selectMajorAll(request,response);
		}
	}

	private void selectMajorAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Major> list = majorService.selectMajors();
		LayuiResponse<Major> layuiResponse = new LayuiResponse<>(list);
		PrintWriter out = response.getWriter();
		out.println(layuiResponse.toString());
	}

	private void getMajorAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strPageSize = request.getParameter("limit"); // 获取每页条数
		String strPageIndex = request.getParameter("page"); // 请求页面
		MajorService majorService = new MajorServiceImpl(); // 获取操作数据库对象
		if (strPageIndex == null || "".equals(strPageIndex) || strPageSize == null || "".equals(strPageSize)) {
			response.sendError(400);
		}
		int totalRows = majorService.getCount(); // 求结果集
		PrintWriter printWriter = response.getWriter();
		String str = null;
		PageMethods<Major> pageMethods = null; // 实例页面操作对象
		int pageIndex = Integer.parseInt(strPageIndex);
		int pageSize = Integer.parseInt(strPageSize);
		try {
			pageMethods = new PageMethods<>(totalRows, pageSize, pageIndex);
		} catch (IndexOutOfPageException e1) {
			response.sendError(400);
		}
		try {
			str = majorService.getMajorsByPage(pageMethods);
		} catch (IndexOutOfPageException e) {
			pageMethods.toJSON(200, "数据异常");
		}
		printWriter.print(str);
	}

	private void addMajor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("开始增加");
		String majorName = request.getParameter("majorName");
		String strfacultyId = request.getParameter("facultyId");
		Integer facultyId = new Integer(strfacultyId);
		Major major = new Major();
		major.setMajorName(majorName);
		major.setFacultyId(facultyId);
		boolean flag = majorService.addMajor(major);
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void delMajor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始删除");
		Integer majorId = Integer.valueOf(request.getParameter("majorId"));
		boolean flag = majorService.deleteMajor(majorId);
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void updateMajor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("开始修改");
		Integer majorId=Integer.valueOf(request.getParameter("majorId"));
		String majorName=request.getParameter("majorName");
		Integer facultyId=Integer.valueOf(request.getParameter("facultyId"));
		System.out.println(facultyId);
		Major major = new Major();
		major.setFacultyId(facultyId);
		major.setMajorId(majorId);
		major.setMajorName(majorName);
		System.out.println("新值："+major);
		boolean flag = majorService.updateMajor(major);
		PrintWriter out=response.getWriter();
		out.println(flag);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
