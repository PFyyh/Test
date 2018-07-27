package com.util.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Field;
import com.pesystem.entity.FileBean;
import com.yyh.MyUtil.MyString;
import com.yyh.util.FileUtil;

import net.sf.json.JSONObject;


public class FileDispose {
	private static String DIECTORY_PATH;
	private static String TEMP_PATH;
	private List<FileBean> fileBeans;
	private String[] supportFileSuffixs;
	
	/**
	 * �޲ι���
	 */
	private FileDispose() {
		fileBeans = new ArrayList<>();
		DIECTORY_PATH = UploadConfig.getDiectoryPath();
		TEMP_PATH = UploadConfig.getTempPath();
	}

	/**
	 * �вι���
	 * 
	 * @param fileBean
	 */
	public FileDispose(String... supportFileSuffixs) {
		this();
		this.supportFileSuffixs = supportFileSuffixs;
	}
	
	
	
	/**
	 * ��ֹһ��Ŀ¼���ڹ�����ļ���ͨ��ʹ�ù�ϣ��������Ŀ¼
	 * 
	 * @return
	 */
	private String createHashDirectory(String fileName) {
		int hashFileName = fileName.hashCode();// ����ļ����Ĺ�ϣ��
		String binFileName = Integer.toBinaryString(hashFileName); // ת������
		binFileName = MyString.characterFill(32, binFileName, '0');// ��λ0
		int hashFirstDirectory = hashFileName & 0xf; // ��͵�4λ��ʮ�����Ƶ�15���������㣬����1111�����Դ������ʮ����Ŀ¼
		System.out.println("hashFirstDirectory:" + Integer.toHexString(hashFirstDirectory)); // һ��Ŀ¼����

		int hashSecondDirectory = (hashFileName >> 4) & 0xf;// ��5-8λ��ʮ�����Ƶ�15���������㣬ͬ��
		File fileDirectory = new File(
				Integer.toHexString(hashFirstDirectory) + File.separator + Integer.toHexString(hashSecondDirectory));
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
		}
		FileUtil.showFiles(DIECTORY_PATH, 0);
		return fileDirectory.getPath();

	}

	/**
	 * 1.����DiskFileItemFactory����
	 * 2.���û�������С�����ļ���С������������С��ʱ�򣬾ͻ�����һ����ʱ���ļ���ŵ�ָ������ʱĿ¼���档 3.�����ļ��ϴ������� 4.�����ļ��ϴ�����
	 * 5.�����ϴ��ļ����������� 6.�ж��ύ�������Ƿ����ϴ��������� 7.�ǵĻ���������� 8.���ǵĻ�������� 9.�����ϴ������ļ��Ĵ�С���ֵ
	 * 10.�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ�����ļ��Ĵ�С�����ֵ�ĺ�
	 * 11.ʹ��servletFileUpload�������ϴ����ݣ����������һ��List<FileItem>�ļ��ϣ�ÿ��fileItem��Ӧ��һ��form���������
	 * 12.����FileItem�����Ķ��������жϣ��������ͨ���������ݣ���ֱ�ӻ�ȡ����getFieldName()��ֵgetString("utf-8");�������롣
	 * 13.������ϴ��ļ��Ļ����õ��ϴ��ļ������ơ� 14.�õ���չ�� 15.���ü��� 16.���ñ���·�� 17.�����ļ������ 18.ͨ�������ļ�д��
	 * 19.�ر��� 20.ɾ�����ɵ���ʱ�ļ� 21.������ʾ
	 */
	public boolean upLoadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = this.getAbsPath(TEMP_PATH);//������ʱĿ¼
		FileUtil.createDiectorys(savePath);
		UploadHandler uploadHandler = new UploadHandler();// �ϴ��ļ�������������ʱ�ļ���
		request.setCharacterEncoding("utf-8");// ��������ͷ����Ϊutf-8
		if (!ServletFileUpload.isMultipartContent(request)) {// �ж��ύ�������Ƿ����ϴ���������,��̬����
			return false;
		}
		try {
			List<FileItem> fileItems = uploadHandler.getFileItems(request);	// ����FileItem
			this.parseFileItems(fileItems);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void parseFileItems(List<FileItem> fileItems) throws IOException {
		for (FileItem fileItem : fileItems) {
			if (fileItem.isFormField()) {// �������ͨ�ı�����
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");// �����ͨ����������ݵ�������������
				System.out.println(name + value);
			} else {// ������ϴ��ļ��Ļ�
				String fileName = fileItem.getName();// ����ļ���
				if (fileName == null || "".equals(fileItem)) {// ���Ϊ�ս�����������ļ�
					continue;
				}
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);// ��ȡ�ļ���
				String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);// �����չ��
				if (!FileUtil.checkFileSuffixs(supportFileSuffixs, fileName)) {// �ж��ϴ�����չ���Ƿ���֧�ֵģ���֧�������
					continue;
				}
				String fileAlias = this.setFileAlias();// �����ļ���������ֹ�ļ�����
				String filePath = this.createHashDirectory(fileAlias);// ����Ŀ¼��ֹͬһĿ¼�ļ�����
				FileBean fileBean = new FileBean(fileName, filePath, fileSuffix, fileAlias);// �������
				fileBeans.add(fileBean);// �����ļ��б�
				InputStream is = fileItem.getInputStream();// �����ļ�
				String relativePath = fileBean.getFilePath();// ��ȡ�洢·��
				String savePath = this.getAbsPath(DIECTORY_PATH + File.separatorChar + relativePath);
				FileUtil.createDiectorys(savePath);// ����·��
				FileUtil.saveFile(is, savePath+File.separatorChar + fileBean.getFileAlias() + "." + fileBean.getFileSuffix());
			}
		}
	}
	
	public File getFirstFile(){
		File file = new File(DIECTORY_PATH + File.separatorChar + fileBeans.get(0).getrelativePath());
		return file;
		
	}
	private String getAbsPath(String path) {
		System.out.println(path);
		String webINFPath = FileUtil.absWEBINFPath();
		String savePath = webINFPath  + path;
		return savePath;
	}

	/**
	 * �����ļ����� �ļ����������������¼�+1~100֮��������
	 */
	private String setFileAlias() {
		DateFormat dataFormat = new SimpleDateFormat("yyyyMMddhhmmss");// ���������ʽ
		Date date = new Date(System.currentTimeMillis()); // ��ȡ����ʱ��
		String strDate = dataFormat.format(date); // ��ʽ���������ʱ��
		int intRandom = (int) (1 + Math.random() * (100 - 1 + 1)); // ����1~100�������
		return strDate + intRandom;
	}
	
	public List<FileBean> getFileBeans() {
		return fileBeans;
	}

	public void setFileBeans(List<FileBean> fileBeans) {
		this.fileBeans = fileBeans;
	}
	
	public String responseToJSON(int code,String msg){
		JSONObject userJSON = new JSONObject();
		userJSON.put("code", code);
		userJSON.put("msg", msg);
		userJSON.put("data", fileBeans);
		return userJSON.toString();
	}
}
