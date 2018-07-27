<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>首页</title>
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
		<div class="layui-row layui-col-space10">
			<div class="layui-col-lg6 layui-col-xs12">
				<blockquote class="layui-elem-quote title">乒乓球1班立定跳远测试</blockquote>
				<form class="layui-form">
					<div class="layui-form-item layui-row magt10" >
						<div class="layui-col-xs3 layui-center">
							<label>学号</label>
						</div>
						<div class="layui-col-xs4 layui-center">
							<label>姓名</label> </div>
						<div class="layui-col-xs5 layui-center">
							<label>成绩</label>
						</div>
					</div>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs3 ">
							<input name="stuNo" type="text" value="154215464" disabled class="layui-input layui-center" style="background-color: #FFFFFF;text-align: center;border: none;">
						</div>
						<div class="layui-col-xs4">
							<input name="stuName" type="text" value="王霸气" disabled class="layui-input layui-center" style="background-color: #FFFFFF;text-align: center;border:none">
						</div>
						<div class="layui-col-xs5">
								<input name="testRecord" type="text" value="学生" class="layui-input layui-center">
						</div>
					</div>

				</form>
			</div>
		</div>

		<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/main.js"></script>
	</body>

</html>