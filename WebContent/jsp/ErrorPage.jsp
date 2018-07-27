
<%@page import="java.util.Enumeration"%>
<%@page import="javax.xml.ws.Response"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title><%=exception %></title>
		<!-- Bootstrap core CSS -->
		<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
		<!-- FONT AWESOME CSS -->
		<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet" />
		<!--GOOGLE FONT -->
		<link href='http://fonts.googleapis.com/css?family=Nova+Flat' rel='stylesheet' type='text/css'>
		<!-- custom CSS here -->
		<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" />
	</head>

	<body>
		<%!
			int status = 403;
			String msg =null;
		%>
		<%
		status = response.getStatus();
		msg = (String)request.getAttribute("msg");
		if(status==404){
			msg = new String("勇士，这里没有恶魔，去其他地方拯救世界吧！");
		} else if(status==500){
			msg = new String("勇士，我们有困难！联系当地管理者一起解决问题！");
		} else if(status==200){
			msg = new String("勇士，这里你不能直接进来！");
		} else if(status==401){
			msg = new String("我们，不认识你！你是谁？");
		} else if(status==403){
			msg = new String("亲爱的，不同人有不同人的活法，做你自己应该做的吧~");
		}
		%>
		<% %>
		<div class="container">
			
			<div class="row pad-top justify-content-center">
				<div class="col-md-6 text-center align-self-center">
					<h1>你干了什么？</h1>
					<h5></h5>
					<span id="error-link"></span>
					<h4>! <%=msg %> !</h4>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-8 text-center">
					<h3> <i  class="fa fa-lightbulb-o fa-5x"><input class='status' type='hidden' name='status' value='<%=status  %>'/></i> </h3>
					<a href="#" class="btn btn-primary">回到主城</a>
				</div>
			</div>
		</div>
		<!-- /.container -->

		<!--Core JavaScript file  -->
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<!--bootstrap JavaScript file  -->
		<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
		<!--Count Number JavaScript file  -->
		<script src="<%=request.getContextPath()%>/js/countUp.js"></script>
		<!--Custom JavaScript file  -->
		<script src="<%=request.getContextPath()%>/js/custom.js"></script>
	</body>
</html>