<%@page import="java.util.*"%>
<%@page import="com.pesystem.dao.impl.AdminDaoImpl"%>
<%@page import="com.pesystem.entity.Admin"%>
<%@page import="com.pesystem.dao.MysqlBaseDao"%>
<%@page import="com.pesystem.dao.AdminDao"%>
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
		List<Admin> results = new ArrayList<>();
		Admin oldAdmin = new Admin("yyh", "uu", "sfd", "sdf", "sdf");
		Admin admin = new Admin("root", "userName", "userTel", "100@qq.com", "qe");
		MysqlBaseDao.getAdminConnection();
		AdminDao adminDao = new AdminDaoImpl();
		List<Admin> admins = new ArrayList<>();
		admins.add(admin);
		admins.add(new Admin("bahtt", "userName", "userTel", "100@qq.com", "qe"));
		results = adminDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>原始数据</h3>
	<table>
		<c:forEach var="admin" items="${list}">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>邮箱</td>
				<td>密码</td>
				<td>电话号码</td>
			</tr>
			<tr>
				<td><c:out value="${admin.userId }"></c:out></td>
				<td><c:out value="${admin.userName }"></c:out></td>
				<td><c:out value="${admin.userEmail }"></c:out></td>
				<td><c:out value="${admin.userPwd }"></c:out></td>
				<td><c:out value="${admin.userTel }"></c:out></td>
			</tr>
		</c:forEach>
	</table>

	<%
		adminDao.update(oldAdmin) ;
		results = adminDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>更新后数据</h3>
	<table>
		<c:forEach var="admin" items="${list}">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>邮箱</td>
				<td>密码</td>
				<td>电话号码</td>
			</tr>
			<tr>
				<td><c:out value="${admin.userId }"></c:out></td>
				<td><c:out value="${admin.userName }"></c:out></td>
				<td><c:out value="${admin.userEmail }"></c:out></td>
				<td><c:out value="${admin.userPwd }"></c:out></td>
				<td><c:out value="${admin.userTel }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		adminDao.insert(admins);
		results = adminDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>导入后数据</h3>
	<table>
		<c:forEach var="admin" items="${list}">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>邮箱</td>
				<td>密码</td>
				<td>电话号码</td>
			</tr>
			<tr>
				<td><c:out value="${admin.userId }"></c:out></td>
				<td><c:out value="${admin.userName }"></c:out></td>
				<td><c:out value="${admin.userEmail }"></c:out></td>
				<td><c:out value="${admin.userPwd }"></c:out></td>
				<td><c:out value="${admin.userTel }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<%
		adminDao.delete("root");
		adminDao.delete("bahtt");
		results = adminDao.select("true");
		request.setAttribute("list", results);
	%>
	<h3>删除后数据</h3>
	<table>
		<c:forEach var="admin" items="${list}">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>邮箱</td>
				<td>密码</td>
				<td>电话号码</td>
			</tr>
			<tr>
				<td><c:out value="${admin.userId }"></c:out></td>
				<td><c:out value="${admin.userName }"></c:out></td>
				<td><c:out value="${admin.userEmail }"></c:out></td>
				<td><c:out value="${admin.userPwd }"></c:out></td>
				<td><c:out value="${admin.userTel }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>