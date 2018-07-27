package com.pesystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesystem.entity.PEClass;
import com.pesystem.service.PEClassService;
import com.pesystem.service.impl.PEClassServiceImpl;
import com.util.page.IndexOutOfPageException;
import com.util.upload.FileDispose;
import com.yyh.MyUtil.MyString;
import com.yyh.util.LayuiResponse;

/**
 * Servlet implementation class PEClassController
 */
public class PEClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PEClassService peClassService = new PEClassServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PEClassController() {
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
		} else if ("getPEClassAll".equals(method)) {
			searchPage(request, response);
		} else if ("addPEClass".equals(method)) {
			addPEClass(request, response);
		} else if ("updatePEClass".equals(method)) {
			updatePEClass(request, response);
		} else if ("delPEClass".equals(method)) {
			delPEClass(request, response);
		} else if ("selectPEClassAll".equals(method)) {
			selectPEClassAll(request, response);
		} else {
			PrintWriter printWriter = response.getWriter();
			printWriter.println(false);
		}

	}

	private void selectPEClassAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<PEClass> list = peClassService.selectAll();
		LayuiResponse<PEClass> layuiResponse = new LayuiResponse<>(list);
		PrintWriter out = response.getWriter();
		out.println(layuiResponse.toString());

	}

	/**
	 * @throws IOException
	 * 			@Title: delPEClass @Description:
	 *             TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 *             response @return: void @throws
	 */
	private void delPEClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String peclassId = request.getParameter("peclassId");
		System.out.println("peclassId=" + peclassId);
		String resultJson = "{\"peclassId\":" + peclassId + "}";
		PrintWriter out = response.getWriter();
		new String(resultJson.getBytes(), "UTF-8");
		System.out.println(resultJson);
		out.println(resultJson);
	}

	/**
	 * @throws IOException
	 * 			@Title: updatePEClass @Description:
	 *             TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 *             response @return: void @throws
	 */
	private void updatePEClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始修改");

		String peclassId = request.getParameter("peclassId");
		String peclassName = request.getParameter("peclassName");
		String peclassTime = request.getParameter("peclassTime");
		String teacherId = request.getParameter("teacherId");
		System.out.println("peclassId=" + peclassId);
		System.out.println("peclassName=" + peclassName);
		System.out.println("peclassTime=" + peclassTime);
		System.out.println("teacherId=" + teacherId);
		String resultJson = "{\"peclassId\":" + peclassId + "}";
		System.out.println(resultJson);
		PrintWriter out = response.getWriter();
		new String(resultJson.getBytes(), "UTF-8");
		out.println(resultJson);
	}

	/**
	 * @throws IOException
	 * 			@Title: addPEClass @Description:
	 *             TODO(这里用一句话描述这个方法的作用) @param: @param request @param: @param
	 *             response @return: void @throws
	 */
	private void addPEClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开始添加");
		PrintWriter out = response.getWriter();
		String peclassId = request.getParameter("peclassId");
		String peclassName = request.getParameter("peclassName");
		String peclassTime = request.getParameter("peclassTime");
		String teacherId = request.getParameter("teacherId");
		if (MyString.isEmpty(peclassId, peclassName, teacherId,peclassTime)) {
			out.print(false);
		} else {
			PEClass peClass = new PEClass();
			peClass.setClassId(peclassId);
			peClass.setClassName(peclassName);
			Integer classYear = new Integer(peclassTime);
			peClass.setClassYear(classYear);
			peClass.setTesterId(teacherId);
			boolean flag = peClassService.addPEClass(peClass);
			out.println(flag);
		}
	}


	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileSuffix = "xlsx";
		FileDispose fileDispose = new FileDispose(fileSuffix);
		fileDispose.upLoadFile(request, response);
		File file = fileDispose.getFirstFile();
		File xlsx = new File(
				request.getSession().getServletContext().getRealPath("") + File.separatorChar + file.getPath());
		boolean flag = false;
		if (peClassService.importPEClasses(xlsx)) {
			flag = peClassService.importStudents(xlsx);
		}
		int code = 0;
		String mString = "";
		if (flag) {
			code = 1;
		}
		PrintWriter pw = response.getWriter();
		pw.write(fileDispose.responseToJSON(code, mString));

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
			str = peClassService.getPEClassByPage(pageSize, pageIndex);
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
