<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>体质测试</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css" media="all" />
</head>
<body class="childrenBody">

	<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<form class="layui-form">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入查询的内容" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">查询</a>
				</form>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addRecord_btn">添加</a>
				<a class="layui-btn layui-btn-normal editRecord_btn">修改</a>
				<a class="layui-btn layui-btn-normal layui-btn-danger delRecord_btn">删除</a>
			</div>
			<div class="layui-inline">
			<a class="layui-btn layui-btn-normal uploadRecord">批量添加</a>
		</div>
	</blockquote>
	<table id="RecordList" lay-filter="RecordList" class="layui-table"></table>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/page/record/RecordList.js"></script>
</body>
</html>