package com.pesystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.pesystem.entity.Faculty;
import com.pesystem.service.FacultyService;
import com.pesystem.service.impl.FacultyServiceImpl;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class FacultyController
 */
public class FacultyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FacultyService facultyService = new FacultyServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacultyController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		PrintWriter out = response.getWriter();
		if ("getFacultyAll".equals(method)) {
			getFacultyAll(request, response);
		} else if ("selectAll".equals(method)){
			selectAll();
		}else if (checkAdminAccount(request, response)) {
			if ("delFaculty".equals(method)) {
				delFaculty(request, response);
			} else if ("addFaculty".equals(method)) {
				addFaculty(request, response);
			} else if ("updateFaculty".equals(method)) {
				updateFaculty(request, response);
			}
		} else {
			out.println(false);
		}
	}

	private void selectAll() {
		facultyService.selectFaculties();
	}

	private void updateFaculty(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("开始修改");
		Integer facultyId = Integer.valueOf(request.getParameter("facultyId"));
		String facultyName = request.getParameter("facultyName");
		System.out.println("facultyId=" + facultyId);
		System.out.println("facultyName=" + facultyName);
		Faculty faculty = new Faculty();
		faculty.setFacultyId(facultyId);
		faculty.setFacultyName(facultyName);
		boolean flag = facultyService.renameFaculty(faculty);
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void addFaculty(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("开始添加");
		String facultyName = request.getParameter("facultyName");
		if ("".equals(facultyName) || null == facultyName) {
			response.sendError(401);
		}
		boolean flag = facultyService.addFaculty(facultyName);
		PrintWriter out = response.getWriter();
		out.println(flag);
	}

	private void delFaculty(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Integer facultyId = Integer.valueOf(request.getParameter("facultyId"));
		boolean flag = false;
		if (facultyId != null && !"".equals(facultyId)) {
			flag = facultyService.deleteFaculty(facultyId);
		}
		out.println(flag);
	}

	private void getFacultyAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Faculty> faculties = facultyService.selectFaculties();
		PrintWriter printWriter = response.getWriter();
		LayuiResponse<Faculty> fLayuiResponse = new LayuiResponse<>(faculties);
		printWriter.println(fLayuiResponse);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean checkAdminAccount(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String adminAccount = (String) session.getAttribute("account");
		boolean flag = false;
		if (adminAccount == null || "".equals(adminAccount)) {
			flag = false;
		} else {
			if (adminAccount.equals("admin")) {
				flag = true;
			}
		}
		return flag;
	}
}
