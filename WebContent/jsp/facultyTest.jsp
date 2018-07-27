<%@page import="com.pesystem.dao.FacultyDao"%>
<%@page import="java.util.*"%>
<%@page import="com.pesystem.dao.impl.FacultyDaoImpl"%>
<%@page import="com.pesystem.entity.Faculty"%>
<%@page import="com.pesystem.dao.MysqlBaseDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>
</head>
<body>

	<h1>管理员dao测试</h1>
	<%
		Faculty faculty = new Faculty(1,"计算机工程学院");
		List<Faculty> faculties = new ArrayList<>();
		FacultyDao facultydao = new FacultyDaoImpl(); 
		List<Faculty> results = facultydao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>原始数据</h3>
	<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>编号</td>
				<td>名称</td>
			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.facultyName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		Faculty oldFaculty = new Faculty(5,"新机械工程学院");
		out.println("更新结果："+facultydao.update(oldFaculty));
		results = facultydao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>更新后数据</h3>
		<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>编号</td>
				<td>名称</td>
			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.facultyName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		Faculty newFaculty = new Faculty();
		newFaculty.setFacultyName("名字");
		faculties.add(oldFaculty);
		faculties.add(newFaculty);
		out.println("导入结果："+facultydao.insert(faculties));
		results = facultydao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>导入后数据</h3>
		<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>编号</td>
				<td>名称</td>
			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.facultyName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		out.println("删除结果："+facultydao.delete(19));
		facultydao.delete(20);
		results = facultydao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>删除后数据</h3>
		<table>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>编号</td>
				<td>名称</td>
			</tr>
			<tr>
				<td><c:out value="${list.facultyId }"></c:out></td>
				<td><c:out value="${list.facultyName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>