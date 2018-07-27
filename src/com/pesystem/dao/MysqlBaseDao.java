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
	static Properties properties = null;// ���԰����Ƕ�ȡ�ʹ�����Դ�ļ�����Ϣ
	static {
		properties = new Properties();// ʵ����
		try {
			String strClasses = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println(strClasses);
			String path = strClasses.substring(0,strClasses.lastIndexOf("WEB-INF/"))+"WEB-INF/properties/db.properties";
			InputStream inputStream = new FileInputStream(new File(path));
			properties.load(inputStream); // ��ȡ������
			System.out.println(properties.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// ����������
		try {
			Class.forName(properties.getProperty("mysqlDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��");
		}
	}
	
	/**
	 * ѧ������
	 * @return
	 */
	public static Connection getStuConnection(){
		return getConnection(properties.getProperty("student"),properties.getProperty("studentPwd"));
	} 
	
	
	/**
	 * ��ʦ����
	 * @return
	 */
	public static Connection getTeaConnection(){
		return getConnection(properties.getProperty("teacher"),properties.getProperty("teacherPwd"));
	} 
	
	/**
	 * ������Ա����
	 * @return
	 */
	public static Connection getTesterConnection(){
		return getConnection(properties.getProperty("tester"),properties.getProperty("testerPwd"));
	} 
	
	/**
	 * ����Ա����
	 * @return
	 */
	public static Connection getAdminConnection(){
		return getConnection(properties.getProperty("admin"),properties.getProperty("adminPwd"));
	} 
	
	/**
	 * mysql���ӷ���
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
			System.out.println("���Ӻ�ʱ" + (time2 - time1) + "ms");
			if (conn == null) {
				System.out.println("����ʧ�ܣ�");
			} else {
				System.out.println("���ӳɹ���" + conn);
			}

		} catch (SQLException e) {
			System.out.println("sql����쳣");
		}
		return conn;
	}

	/**
	 * �ر�
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
