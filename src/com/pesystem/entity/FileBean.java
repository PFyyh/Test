package com.pesystem.entity;

import java.io.File;

/**
 * �ļ�������
 * ֻ��ͨ����̬����ʵ��������
 * @author һ������ؼ
 *
 */
public class FileBean {
	private String fileName;
	private String filePath;
	private String fileSuffix;
	private String fileAlias;
	public FileBean(String fileName, String filePath, String fileSuffix, String fileAlias) {
		this();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSuffix = fileSuffix;
		this.fileAlias = fileAlias;
	}
	
	
	private FileBean() {
	}

	public String getrelativePath(){
		return filePath+File.separatorChar+fileAlias+"."+fileSuffix;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileSuffixValue() {
		return fileSuffix;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public String getFileAlias() {
		return fileAlias;
	}

	public void setFileAlias(String fileAlias) {
		this.fileAlias = fileAlias;
	}
}
