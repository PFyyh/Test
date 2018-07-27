<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>学生信息管理</title>
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

	<blockquote class="layui-elem-quote quoteBox">
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal addStuInfor_btn">添加</a> <a
				class="layui-btn layui-btn-normal editStuInfor_btn">修改</a> 
			<button type="button" class="layui-btn" id="uploadStudent">
				<i class="layui-icon">&#xe67c;</i>批量学生信息
			</button>
		</div>
	</blockquote>
	<table id="StuInforList" lay-filter="StuInforList" class="layui-table"></table>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/page/stuInfor/StuInforList.js"></script>
	<script type="text/html" id="sexTpl">
   {{#  if(d.stuSex === 1){ }}
    <span style="color: #F581B1;">男</span>
  {{#  } else {  }}
    <span>女</span>
  {{#  } }}
</script>
</body>
</html>