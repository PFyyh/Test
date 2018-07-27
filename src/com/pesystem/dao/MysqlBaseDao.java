package com.pesystem.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlBaseDao {
	static Properties properties = null;// 可以帮我们读取和处理资源文件的信息
	static {
		properties = new Properties();// 实例化
		try {
			String strClasses = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println(strClasses);
			String path = strClasses.substring(0,strClasses.lastIndexOf("WEB-INF/"))+"WEB-INF/properties/db.properties";
			InputStream inputStream = new FileInputStream(new File(path));
			properties.load(inputStream); // 获取输入流
			System.out.println(properties.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 加载驱动类
		try {
			Class.forName(properties.getProperty("mysqlDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
		}
	}
	
	/**
	 * 学生连接
	 * @return
	 */
	public static Connection getStuConnection(){
		return getConnection(properties.getProperty("student"),properties.getProperty("studentPwd"));
	} 
	
	
	/**
	 * 老师连接
	 * @return
	 */
	public static Connection getTeaConnection(){
		return getConnection(properties.getProperty("teacher"),properties.getProperty("teacherPwd"));
	} 
	
	/**
	 * 测试人员连接
	 * @return
	 */
	public static Connection getTesterConnection(){
		return getConnection(properties.getProperty("tester"),properties.getProperty("testerPwd"));
	} 
	
	/**
	 * 管理员连接
	 * @return
	 */
	public static Connection getAdminConnection(){
		return getConnection(properties.getProperty("admin"),properties.getProperty("adminPwd"));
	} 
	
	/**
	 * mysql连接方法
	 * 
	 * @return
	 */
	public static Connection getConnection(String account,String pwd) {
		String url = properties.getProperty("peSystemURL");
		Connection conn = null;
		try {
			long time1 = System.currentTimeMillis();
			conn = DriverManager.getConnection(url,account,pwd);
			long time2 = System.currentTimeMillis();
			System.out.println("连接耗时" + (time2 - time1) + "ms");
			if (conn == null) {
				System.out.println("连接失败！");
			} else {
				System.out.println("连接成功！" + conn);
			}

		} catch (SQLException e) {
			System.out.println("sql语句异常");
		}
		return conn;
	}

	/**
	 * 关闭
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
