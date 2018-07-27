package com.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UploadConfig {
	static Properties uploadConfig = null;// 可以帮我们读取和处理资源文件的信息
	static {
		uploadConfig = new Properties();// 实例化
		try {
			String strClasses = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String path = strClasses.substring(0,strClasses.lastIndexOf("WEB-INF/"))+"WEB-INF/properties/upload.properties";
			InputStream inputStream = new FileInputStream(new File(path));
			uploadConfig.load(inputStream); // 获取输入流
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static String getDiectoryPath(){
		return uploadConfig.getProperty("filePath");
	}
	
	public static String getTempPath(){
		return uploadConfig.getProperty("tempPath");
	}
}
