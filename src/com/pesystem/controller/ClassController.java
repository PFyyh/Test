package com.pesystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.dao.ClassDao;
import com.pesystem.entity.Clazz;
import com.pesystem.service.ClassService;
import com.pesystem.service.impl.ClassServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.yyh.MyUtil.MyString;
import com.yyh.util.LayuiResponse;

import sun.print.PrinterGraphicsConfig;

/**
 * Servlet implementation class ClassController
 */
public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClassService classService = new ClassServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("upload".equals(method)) {
			upload(request, response);
		} else if ("getClassAll".equals(method)) {
			getClassAll(request, response);
		} else if ("addClass".equals(method)) {
			addClass(request, response);
		} else if ("updateClass".equals(method)) {
			updateClass(request, response);
		} else if ("delClass".equals(method)) {
			delClass(request, response);
		} else if ("selectAll".equals(method)) {
			selectAll(request, response);
		} else {
			response.sendError(401);
		}
	}

	private void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Clazz> list = classService.selectAll();
		LayuiResponse<Clazz> layuiResponse = new LayuiResponse<>(list);
		PrintWriter printWriter = response.getWriter();
		printWriter.println(layuiResponse.toString());
	}

	/**
	 * @throws IOException @Title: delClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void delClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String classId = request.getParameter("classId");
		boolean flag = false;
		PrintWriter out = response.getWriter();
		if (classId==null||"".equals(classId)) {
		}else {
			classService.deleteClazz(classId);
		}
		
	}

	/**
	 * @throws IOException @Title: updateClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void updateClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始修改");
		PrintWriter out = response.getWriter();
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		String testerId = request.getParameter("testerId");
		String majorId = request.getParameter("majorId");
		String classYear = request.getParameter("classYear");
		if (MyString.isEmpty(classId, className, testerId, majorId, classYear)) {
			out.println(false);
		}
		Integer imajorId = new Integer(majorId);
		Integer year = new Integer(classYear);
		Clazz clazz = new Clazz();
		clazz.setClassId(classId);
		clazz.setClassName(className);
		clazz.setClassYear(year);
		clazz.setTesterId(testerId);
		clazz.setMajorId(imajorId);
		boolean flag = classService.updateClazz(clazz);
		out.println(flag);
	}

	/**
	 * @throws IOException @Title: addClass @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void addClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始添加");
		PrintWriter out = response.getWriter();
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		String testerId = request.getParameter("testerId");
		String majorId = request.getParameter("majorId");
		String classYear = request.getParameter("classYear");
		if (MyString.isEmpty(classId, className, testerId, majorId, classYear)) {
			out.println(false);
		}
		Integer imajorId = new Integer(majorId);
		Integer year = new Integer(classYear);
		Clazz clazz = new Clazz();
		clazz.setClassId(classId);
		clazz.setClassName(className);
		clazz.setClassYear(year);
		clazz.setTesterId(testerId);
		clazz.setMajorId(imajorId);
		boolean flag = classService.addClass(clazz);
		out.println(flag);
	}

	/**
	 * @throws IOException @Title: getClassAll @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 * response @return: void @throws
	 */
	private void getClassAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		searchPage(request, response);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void searchPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strPageSize = request.getParameter("limit"); // ��ȡÿҳ����
		String strPageIndex = request.getParameter("page"); // ����ҳ��
		if (strPageIndex == null || "".equals(strPageIndex) || strPageIndex == null || "".equals(strPageIndex)) {
			response.sendError(400);
		}
		PrintWriter printWriter = response.getWriter();
		String str = null;
		int pageIndex = Integer.parseInt(strPageIndex);
		int pageSize = Integer.parseInt(strPageSize);

		try {
			str = classService.geClassesByPage(pageSize, pageIndex);
		} catch (IndexOutOfPageException e) {
			response.sendError(500);
		}
		printWriter.print(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
