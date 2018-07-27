package com.pesystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.entity.Admin;
import com.pesystem.service.AdminService;
import com.pesystem.service.impl.AdminServiceImpl;
import com.yyh.MyUtil.MyString;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService adminService = new AdminServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println(method);
		if ("getAdminAll".equals(method)) {
			getAdminAll(request, response);
		} else if ("addAdmin".equals(method)) {
			addAdmin(request, response);
		} else if ("updateAdmin".equals(method)) {
			updateAdmin(request, response);
		} else if ("delAdmin".equals(method)) {
			delAdmin(request, response);
		}
	}

	private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		String adminTel = request.getParameter("adminTel");
		String adminEmail = request.getParameter("adminEmail");
		PrintWriter out = response.getWriter();

		boolean flag = false;
		if (MyString.isEmpty(adminId, adminName, adminTel, adminEmail)) {
		} else {
			Admin admin = new Admin();
			admin.setUserId(adminId);
			admin.setUserName(adminName);
			admin.setUserTel(adminTel);
			admin.setUserEmail(adminEmail);
			flag = adminService.insert(admin);
		}
		out.println(flag);

	}

	private void delAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String adminId = request.getParameter("adminId");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (MyString.isEmpty(adminId)) {
		} else {
			Admin admin = new Admin();
			admin.setUserId(adminId);
			flag = adminService.deleteAdmin(admin);
		}
		out.println(flag);
	}

	private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String adminId = request.getParameter("adminId");
		System.out.println(adminId);
		String adminName = request.getParameter("adminName");
		String adminTel = request.getParameter("adminTel");
		String adminEmail = request.getParameter("adminEmail");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (MyString.isEmpty(adminName,adminId,adminTel,adminEmail)) {
		}else{
			Admin admin = new Admin();
			admin.setUserId(adminId);
			admin.setUserName(adminName);
			admin.setUserTel(adminTel);
			admin.setUserEmail(adminEmail);
			flag = adminService.updateAdmin(admin);
		}
		out.println(flag);
	}

	private void getAdminAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Admin> admins = adminService.selectAdmin();
		LayuiResponse<Admin> layuiResponse = new LayuiResponse<>(admins);
		PrintWriter out = response.getWriter();
		out.println(layuiResponse.toString());
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
