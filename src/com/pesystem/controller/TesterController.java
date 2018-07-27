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
import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.pesystem.service.TesterService;
import com.pesystem.service.impl.TesterServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class TesterController
 */
public class TesterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TesterService testerService = new TesterServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TesterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		System.out.println(method);
		if ("getTesterAll".equals(method)) {
			getTesterAll(request, response);
		} else if ("addTester".equals(method)) {
			addTester(request, response);
		} else if ("updateTester".equals(method)) {
			updateTester(request, response);
		} else if ("delTester".equals(method)) {
			delTester(request, response);
		}else if ("selectTesterAll".equals(method)) {
			selectTesterAll(request,response);
		} {
			
		}
	}

	private void selectTesterAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Tester> list = testerService.selectAll();
		LayuiResponse<Tester> layuiResponse = new LayuiResponse<>(list);
		PrintWriter out = response.getWriter();
		out.println(layuiResponse.toString());
		
		
	}

	private void delTester(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String testerId = request.getParameter("teacherId");
		boolean flag = false;
		if (testerId!=null) {
			Tester tester = new Tester();
			tester.setUserId(testerId);
			flag = testerService.deleteTester(tester);
		}
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void updateTester(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String testerId = request.getParameter("testerId");
		System.out.println(testerId);
		String userName = request.getParameter("testerName");
		String userTel = request.getParameter("testerTel");
		String userEmail = request.getParameter("testerEmail");
		boolean flag = false;
		PrintWriter out = response.getWriter();
		if (userName==null||"".equals(userName)||userTel==null||"".equals(userTel)||userEmail==null||"".equals(userEmail)||testerId==null||"".equals(testerId)) {
			out.print(flag);
		}else{
			Tester tester = new Tester();
			tester.setUserName(userName);
			tester.setUserTel(userTel);
			tester.setUserEmail(userEmail);
			tester.setUserId(testerId);
			flag = testerService.updateTester(tester);
			out.println(flag);
		}		
	}

	private void addTester(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String testerId = request.getParameter("testerId");
		System.out.println(testerId);
		String userName = request.getParameter("testerName");
		String userTel = request.getParameter("testerTel");
		String userEmail = request.getParameter("testerEmail");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (userName==null||"".equals(userName)||userTel==null||"".equals(userTel)||userEmail==null||"".equals(userEmail)) {
			out.print(flag);
		}else{
			Tester tester = new Tester();
			tester.setUserName(userName);
			tester.setUserTel(userTel);
			tester.setUserEmail(userEmail);
			tester.setUserId(testerId);
			flag = testerService.addTester(tester);
			out.println(flag);
		}		
	}

	private void getTesterAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		searchPage(request,response);
	}

	private void searchPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("TesterController.searchPage()");
		String strPageSize = request.getParameter("limit"); // 获取每页条数
		String strPageIndex = request.getParameter("page"); // 请求页面
		if (strPageIndex == null || "".equals(strPageIndex) || strPageIndex == null || "".equals(strPageIndex)) {
			response.sendError(400);
		}
		PrintWriter printWriter = response.getWriter();
		String str = null;
		int pageIndex = Integer.parseInt(strPageIndex);
		int pageSize = Integer.parseInt(strPageSize);

		try {
			str = testerService.getTestersByPage(pageSize, pageIndex);
		} catch (IndexOutOfPageException e) {
			response.sendError(500);
		}
		printWriter.print(str);
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

}
