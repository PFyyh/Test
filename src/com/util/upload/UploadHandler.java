package com.util.upload;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * ����Ҫ���԰����������Ϊ�����ļ����������ӷ���
 * @author һ������ؼ
 *
 */
public class UploadHandler {
	private static DiskFileItemFactory diskFileItemFactory;
	private static ServletFileUpload servletFileUpload;
	static{		
		diskFileItemFactory = new DiskFileItemFactory();//ʵ�����ļ�����
		diskFileItemFactory.setSizeThreshold(1024 * 100); // ����Ϊ100kB,������Ĭ��10kb
		servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(1024 * 1024);// �����ϴ�����ļ���С�����ֵ��Ŀǰ�����õ���1024*1024�ֽڣ�1MB
		servletFileUpload.setFileSizeMax(1024 * 1024 * 10);// �����ϴ��ļ����������ֵ������һ��������������С����
	}
	public UploadHandler() {
	}
	public static ServletFileUpload getServletFileUpload(){
		return servletFileUpload;
	}
	
	public List<FileItem> getFileItems(HttpServletRequest request) throws FileUploadException{
		return servletFileUpload.parseRequest(request);
	}
	
}
