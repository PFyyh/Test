<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="loginHtml">

<head>
<meta charset="utf-8">
<title>登录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css" media="all" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/public.css" media="all" />
</head>

<body class="loginBody">
	<div class="login_welcome">欢迎使用体质健康管理系统</div>
	<div class="login_error">
		<c:out value="${msg}"></c:out>
	</div>
	<form class="layui-form"
		action="<%=request.getContextPath()%>/Login.do"
		method="post">
		<input type="hidden" name="method" value="login"/>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label> <input name="userName" type="text"
				placeholder="请输入用户名" autocomplete="off" id="userName"
				class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label> <input name="userPass"
				type="password" placeholder="请输入密码" autocomplete="off" id="password"
				class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item" id="imgCode">
			<label for="code">验证码</label> <input name="verCode" type="text"
				placeholder="请输入验证码" autocomplete="off" id="code" 
				class="layui-input"> <img
				src="<%=request.getContextPath()%>/Login.do?method=getVercode">
		</div>
		<div class="layui-form-item">
			<div class="userIdentity">
				<input type="radio" name="identity" value="admin" title="管理员"
					checked="true"> <input type="radio" name="identity"
					value="teacher" title="教师"> <input type="radio"
					name="identity" value="tester" title="测试员"> <input
					type="radio" name="identity" value="student" title="学生">
			</div>
		</div>
		<div class="layui-form-item magt60">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
		</div>
	</form>
	<div class="layui-footer" style="text-align: center; color: #FFF9EC;">
		<!-- 底部固定区域 -->
		&copy;2018 【软件工程综合实践三小组】 All rights reserved.
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/page/login/login.js"></script>
</body>

</html>