package com.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UploadConfig {
	static Properties uploadConfig = null;// ���԰����Ƕ�ȡ�ʹ�����Դ�ļ�����Ϣ
	static {
		uploadConfig = new Properties();// ʵ����
		try {
			String strClasses = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String path = strClasses.substring(0,strClasses.lastIndexOf("WEB-INF/"))+"WEB-INF/properties/upload.properties";
			InputStream inputStream = new FileInputStream(new File(path));
			uploadConfig.load(inputStream); // ��ȡ������
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
