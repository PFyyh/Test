<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>体育班级管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css" media="all" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/public.css" media="all" />
</head>
<body class="childrenBody">

	<c:if test="${account=='admin'}">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addPEClass_btn">添加</a> 
				<a class="layui-btn layui-btn-normal editPEClass_btn">修改</a> 
				<a class="layui-btn layui-btn-normal layui-btn-danger delPEClass_btn">删除</a>
				<button type="button" class="layui-btn" id="uploadPE">
					<i class="layui-icon">&#xe67c;</i>批量上传体育班级
				</button>
			</div>
		</blockquote>

	</c:if>
	<table id="PEClassList" lay-filter="PEClassList" class="layui-table"></table>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/page/PEClass/PEClassList.js"></script>
</body>
</html>