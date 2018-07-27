package com.util.upload;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 有需要可以把里面参数设为配置文件，这样更加方便
 * @author 一包辣条丶
 *
 */
public class UploadHandler {
	private static DiskFileItemFactory diskFileItemFactory;
	private static ServletFileUpload servletFileUpload;
	static{		
		diskFileItemFactory = new DiskFileItemFactory();//实例化文件工厂
		diskFileItemFactory.setSizeThreshold(1024 * 100); // 设置为100kB,不设置默认10kb
		servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(1024 * 1024);// 设置上传大哥文件大小的最大值，目前是设置的是1024*1024字节，1MB
		servletFileUpload.setFileSizeMax(1024 * 1024 * 10);// 设置上传文件总量的最大值，方法一样，设置总量大小即可
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
