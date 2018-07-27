<%@page import="com.pesystem.dao.*"%>
<%@page import="java.util.*"%>
<%@page import="com.pesystem.dao.impl.*"%>
<%@page import="com.pesystem.entity.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>管理员dao测试</h1>
	<%
		List<Major> results = new ArrayList<>();
		Major oldMajor = new Major();
		oldMajor.setFacultyId(13);
		oldMajor.setMajorName("计算机科学与技术");
		MysqlBaseDao.getAdminConnection();
		MajorDao majorDao = new MajorDaoImpl();
		List<Major> majors = new ArrayList<>();
		majors.add(oldMajor);
		results = majorDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>原始数据</h3>
	<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>学院编号</td>
				<td>专业编号</td>
				<td>专业名称</td>

			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.majorId }"></c:out></td>
				<td><c:out value="${list.majorName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		out.print("导入是否成功" + majorDao.insert(majors));
		results = majorDao.select("true");
		request.setAttribute("list", results);
	%>
	<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>学院编号</td>
				<td>专业编号</td>
				<td>专业名称</td>

			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.majorId }"></c:out></td>
				<td><c:out value="${list.majorName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		Major newMajor = new Major();
		newMajor.setMajorId(8);
		newMajor.setFacultyId(13);
		newMajor.setMajorName("改名计算机科学与技术");
		out.println("修改是否成功：" + majorDao.update(newMajor));
		results = majorDao.select("true");
		request.setAttribute("list", results);
	%>
	<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>学院编号</td>
				<td>专业编号</td>
				<td>专业名称</td>

			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.majorId }"></c:out></td>
				<td><c:out value="${list.majorName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		out.println(majorDao.delete(8));
		results = majorDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>删除编号为8的</h3>
	<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>学院编号</td>
				<td>专业编号</td>
				<td>专业名称</td>

			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.majorId }"></c:out></td>
				<td><c:out value="${list.majorName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>