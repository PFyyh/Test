package com.pesystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.entity.FileBean;
import com.pesystem.entity.Major;
import com.pesystem.entity.Record;
import com.pesystem.entity.Student;
import com.pesystem.service.MajorService;
import com.pesystem.service.StudentService;
import com.pesystem.service.impl.MajorServiceImpl;
import com.pesystem.service.impl.StudentServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.util.page.PageMethods;
import com.util.upload.FileDispose;
import com.yyh.MyUtil.MyString;

/**
 * Servlet implementation class StudentController
 */
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService studentService = new StudentServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("searchPage".equals(method)) {
			searchPage(request, response);
		} else if ("uploadStudent".equals(method)) {
			upload(request, response);
		}else if ("getStuInforAll".equals(method)) {
			getStuInforAll(request, response);// 查询
		} else if ("updateStuInfor".equals(method)) {
			updateStuInfor(request, response);// 修改
		} else if ("addStuInfor".equals(method)) {
			addStuInfor(request, response);// 添加
		} else if ("StuInforSelect".equals(method)) {
			StuInforSelect(request, response);// 删除
		}  else {
			PrintWriter printWriter = response.getWriter();
			printWriter.println(false);
		}
		
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileDispose fileDispose = new FileDispose("xls","xlsx");
		fileDispose.upLoadFile(request, response);
		File file = fileDispose.getFirstFile();
		File xlsx = new File(
				request.getSession().getServletContext().getRealPath("") + File.separatorChar + file.getPath());
		boolean flag = false;
		flag = studentService.importStudentsByExcel(xlsx); 
		int code = 1;
		String mString = "";
		if (flag) {
			code = 0;
		}
		PrintWriter pw = response.getWriter();
		pw.write(fileDispose.responseToJSON(code, mString));
	}

	private void StuInforSelect(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1111111111111111111111111");
	}

	private void addStuInfor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("添加学生信息");
		String stuNo = request.getParameter("stuNo");
		String classId = request.getParameter("classId");
		String userName = request.getParameter("stuName");
		String stuBirthday = request.getParameter("stuBirthday");
		String stuPwd = request.getParameter("stuPwd");
		String personId = request.getParameter("personId");
		String stuHome = request.getParameter("stuHome");
		String stuSex = request.getParameter("stuSex");
		String stuNation = request.getParameter("stuNation");
		String stuOrigin = request.getParameter("stuOrigin");
		String stuTel = request.getParameter("stuTel");
		String stuEmail = request.getParameter("stuEmail");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (MyString.isEmpty(stuNo, classId, userName, stuBirthday, stuPwd, personId, stuHome, stuSex, stuNation,
				stuOrigin, stuTel, stuEmail)) {
			System.out.println("存在空stuNo:"+stuNo+";stuNo: "+classId+";stuNo: "+userName+";stuNo: "+stuBirthday+";stuNo: "+stuPwd+";stuNo: "+personId+";stuNo: "+stuHome+";stuNo: "+stuSex+";stuNO: "+stuNation);
		}else{
			System.out.println("不为空");
			Student student = new Student();
			student.setUserId(stuNo);
			student.setClassId(classId);
			student.setUserName(userName);
			student.setStuBirthday(stuBirthday);
			student.setUserPwd(stuPwd);
			student.setPersonId(personId);
			student.setStuHome(stuHome);
			Integer integer = new Integer(stuSex);
			student.setStuSex(integer);
			integer = new Integer(stuNation);
			student.setStuNation(integer);
			integer= new Integer(stuOrigin);
			student.setStuOrigin(integer);
			student.setUserTel(stuTel);
			student.setUserEmail(stuEmail);
			flag = studentService.insertStudent(student);
		}
		out.println(flag);
	}

	private void updateStuInfor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("更新学生信息");
		String stuNo = request.getParameter("stuNo");
		String classId = request.getParameter("classId");
		String stuName = request.getParameter("stuName");
		String stuBirthday = request.getParameter("stuBirthday");
		String personId = request.getParameter("personId");
		String stuHome = request.getParameter("stuHome");
		String stuSex = request.getParameter("stuSex");
		String stuNation = request.getParameter("stuNation");
		String stuOrigin = request.getParameter("stuOrigin");
		String stuTel = request.getParameter("stuTel");
		String stuEmail = request.getParameter("stuEmail");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if (MyString.isEmpty(stuNo,  stuName, stuBirthday,personId,  stuSex,stuTel, stuEmail)) {
			System.out.println("---------空处理-------------");
			System.out.println("存在空stuNo:"+stuNo+";stuNo: "+stuName+";stuNo: "+stuBirthday+";stuNo: "+personId+";stuNo: "+stuHome+"stuNo: "+stuSex+";stuNo: "+stuTel+";stuNo: "+stuEmail);
		}else{
			System.out.println("---------空处理-------------");
			System.out.println("不存在空");
			Student student = new Student();
			student.setUserId(stuNo);
			student.setClassId(classId);
			student.setUserName(stuName);
			student.setStuBirthday(stuBirthday);
			student.setPersonId(personId);
			student.setStuHome(stuHome);
			Integer integer = new Integer(stuSex);
			student.setStuSex(integer);
			integer = new Integer(stuNation);
			student.setStuNation(integer);
			integer = new Integer(stuOrigin);
			student.setStuOrigin(integer);
			student.setUserTel(stuTel);
			student.setUserEmail(stuEmail);
			flag = studentService.updateStudentInfor(student);
		}
		out.println(flag);
	}

	private void getStuInforAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		searchPage(request, response);

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
			str = studentService.getStudentsByPage(pageSize, pageIndex);
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
		doGet(request, response);
	}

}
