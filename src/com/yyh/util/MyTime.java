package com.yyh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTime {
	public static int getCurrectYear(){
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Integer year =new Integer(dateFormat.format(date)) ;
		return year;
	}
}
