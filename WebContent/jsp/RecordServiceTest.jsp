<%@page import="com.pesystem.dao.*"%>
<%@page import="java.util.*"%>
<%@page import="com.pesystem.dao.impl.*"%>
<%@page import="com.pesystem.entity.*"%>
<%@page import="com.pesystem.service.impl.RecordServiceImpl"%>
<%@page import="com.pesystem.service.RecordService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Record> records = new ArrayList<>();
	Record record1 = new Record();
	record1.setStuNo("1501511625");
	record1.setYear(2018);
	records.add(record1);
	record1.setSitup(20);
	record1.setFivetyRun("0′22″");
	
	Record record2 = new Record();
	record2.setStuNo("1501511626");
	record2.setYear(2018);
	record2.setSitup(20);
	record2.setFivetyRun("0′75″");
	records.add(record2);
	RecordService recordService = new RecordServiceImpl();
	recordService.inputRecord(records);
%>

<% %>
</body>
</html>