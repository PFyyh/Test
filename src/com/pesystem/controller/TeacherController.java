package com.pesystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.pesystem.service.TeacherService;
import com.pesystem.service.impl.TeacherServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class TeacherController
 */
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeacherService teacherService = new TeacherServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("getTeacherAll".equals(method)) {
			getTeacherAll(request, response);
		} else if ("addTeacher".equals(method)) {
			addTeacher(request, response);
		} else if ("updateTeacher".equals(method)) {
			updateTeacher(request, response);
		} else if ("delTeacher".equals(method)) {
			delTeacher(request, response);
		} else if ("selectTeacherAll".equals(method)) {
			selectTeacherAll(request,response);
		}
	}

	private void selectTeacherAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Teacher> list = teacherService.selectAll();
		LayuiResponse<Teacher> layuiResponse = new LayuiResponse<>(list);
		PrintWriter out = response.getWriter();
		out.println(layuiResponse.toString());
		
		
	}

	private void delTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("teacherId");
		boolean flag = false;
		if (userId!=null) {
			Teacher teacher = new Teacher();
			teacher.setUserId(userId);
			flag = teacherService.deleteTeacher(teacher);
		}
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("teacherId");
		String userName=request.getParameter("teacherName");
		String userTel=request.getParameter("teacherTel");
		String userEmail=request.getParameter("teacherEmail");
		boolean flag = false;
		PrintWriter out = response.getWriter();
		if (userName==null||"".equals(userName)||userTel==null||"".equals(userTel)||userEmail==null||"".equals(userEmail)||userId==null||"".equals(userId)) {
			out.print(flag);
		}else{
			Teacher teacher = new Teacher();
			teacher.setUserName(userName);
			teacher.setUserTel(userTel);
			teacher.setUserEmail(userEmail);
			teacher.setUserId(userId);
			flag = teacherService.updateTeacher(teacher);
			out.println(flag);
		}		
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userName=request.getParameter("teacherName");
		String userTel=request.getParameter("teacherTel");
		String userEmail=request.getParameter("teacherEmail");
		String userId = request.getParameter("teacherId");
		boolean flag = false;
		if (userName==null||"".equals(userName)||userTel==null||"".equals(userTel)||userEmail==null||"".equals(userEmail)) {
			out.print(flag);
		}else{
			Teacher teacher = new Teacher();
			teacher.setUserName(userName);
			teacher.setUserTel(userTel);
			teacher.setUserEmail(userEmail);
			teacher.setUserId(userId);
			flag = teacherService.addTeacher(teacher);
			out.println(flag);
		}		
		
	}

	private void getTeacherAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		searchPage(request,response);
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
		PrintWriter printWriter = response.getWriter();
		String str = null;
		int pageIndex = Integer.parseInt(strPageIndex);
		int pageSize = Integer.parseInt(strPageSize);

		try {
			str = teacherService.getTeachersByPage(pageSize, pageIndex);
		} catch (IndexOutOfPageException e) {
			response.sendError(500);
		}
		printWriter.print(str);
	}
}
