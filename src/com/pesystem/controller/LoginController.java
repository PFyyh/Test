
//
package com.pesystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pesystem.entity.Admin;
import com.pesystem.entity.Student;
import com.pesystem.entity.Teacher;
import com.pesystem.entity.Tester;
import com.pesystem.entity.User;
import com.pesystem.service.AdminService;
import com.pesystem.service.StudentService;
import com.pesystem.service.TeacherService;
import com.pesystem.service.TesterService;
import com.pesystem.service.impl.AdminServiceImpl;
import com.pesystem.service.impl.StudentServiceImpl;
import com.pesystem.service.impl.TeacherServiceImpl;
import com.pesystem.service.impl.TesterServiceImpl;
import com.util.vcode.Captcha;
import com.util.vcode.GifCaptcha;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		if ("login".equals(method)) {
			login(request, response);
		}else if ("getVercode".equals(method)) {
			getVercode(request, response);
		} else {
			response.sendError(401);
		}
	}

	private User getUser(String identity, String userId, String userPwd) {
		User user = null;
		if ("admin".equals(identity)) {
			Admin admin = new Admin();
			admin.setUserId(userId);
			admin.setUserPwd(userPwd);
			AdminService adminService = new AdminServiceImpl();
			user = adminService.getAdmin(admin);
		} else if ("student".equals(identity)) {
			Student student = new Student();
			student.setUserId(userId);
			student.setUserPwd(userPwd);
			StudentService studentService = new StudentServiceImpl();
			user = studentService.selectStudentInfor(student);
		} else if ("tester".equals(identity)) {
			Tester tester = new Tester();
			tester.setUserId(userId);
			tester.setUserPwd(userPwd);
			TesterService testerService = new TesterServiceImpl();
			user = testerService.getTester(tester);
		} else if ("teacher".equals(identity)) {
			Teacher teacher = new Teacher();
			teacher.setUserId(userId);
			teacher.setUserPwd(userPwd);
			TeacherService teacherService = new TeacherServiceImpl();
			user = teacherService.getTeacher(teacher);
		}
		return user;

	}

	/**
	 * @Title: login @Description: TODO(这里用一句话描述这个方法的作用) @param: @param
	 *         request @param: @param response @return: void @throws
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg = "";
		String userName = request.getParameter("userName"); // 获取用户名
		System.out.println("用户名=" + userName);
		String userPass = request.getParameter("userPass"); // 获取密码
		System.out.println("密码=" + userPass);
		String verCode = request.getParameter("verCode"); // 获取验证码
		System.out.println("验证码=" + verCode);
		String identity = request.getParameter("identity"); // 获取身份
		HttpSession session = request.getSession();
		String verCodeTemp = (String) session.getAttribute("codeVer");
		if (userName == null || "".equals(userName) || userPass == null || "".equals(userPass)) {
			response.sendError(401);
		} else if (verCodeTemp.equals(verCode)) {
			User user = getUser(identity, userName, userPass);
			if (user != null) {
				session.setAttribute("account", identity);
				session.setAttribute("user", user);
				if ("admin".equals(identity)) {
					response.sendRedirect(this.getServletContext().getContextPath()+"/index.jsp");
				}else if ("tester".equals(identity)) {
					response.sendRedirect(this.getServletContext().getContextPath()+"/TesterIndex.jsp");
				}else if ("teacher".equals(identity)) {
					response.sendRedirect(this.getServletContext().getContextPath()+"/TesterIndex.jsp");
				}else if ("student".equals(identity)) {
					response.sendRedirect(this.getServletContext().getContextPath()+"/index.jsp");
				}
			}else{
				msg = identity +"用户认证失败";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("page/login/login.jsp").forward(request, response);;
			}
		} else {
			msg = "验证码错误";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("page/login/login.jsp").forward(request, response);;
		}
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
	
	private void getVercode(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			Captcha captcha = new GifCaptcha(146, 40, 4);
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession();
			session.removeAttribute("codeVer");
			session.setAttribute("codeVer", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
