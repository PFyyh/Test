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
	 * 无参构造
	 */
	private FileDispose() {
		fileBeans = new ArrayList<>();
		DIECTORY_PATH = UploadConfig.getDiectoryPath();
		TEMP_PATH = UploadConfig.getTempPath();
	}

	/**
	 * 有参构造
	 * 
	 * @param fileBean
	 */
	public FileDispose(String... supportFileSuffixs) {
		this();
		this.supportFileSuffixs = supportFileSuffixs;
	}
	
	
	
	/**
	 * 防止一个目录存在过多的文件，通过使用哈希码来创建目录
	 * 
	 * @return
	 */
	private String createHashDirectory(String fileName) {
		int hashFileName = fileName.hashCode();// 获得文件名的哈希码
		String binFileName = Integer.toBinaryString(hashFileName); // 转二进制
		binFileName = MyString.characterFill(32, binFileName, '0');// 补位0
		int hashFirstDirectory = hashFileName & 0xf; // 最低的4位和十六进制的15进行与运算，最大可1111，可以创建最多十六个目录
		System.out.println("hashFirstDirectory:" + Integer.toHexString(hashFirstDirectory)); // 一级目录产生

		int hashSecondDirectory = (hashFileName >> 4) & 0xf;// 第5-8位和十六进制的15进行与运算，同理。
		File fileDirectory = new File(
				Integer.toHexString(hashFirstDirectory) + File.separator + Integer.toHexString(hashSecondDirectory));
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
		}
		FileUtil.showFiles(DIECTORY_PATH, 0);
		return fileDirectory.getPath();

	}

	/**
	 * 1.创建DiskFileItemFactory工厂
	 * 2.设置缓冲区大小，当文件大小超过缓冲区大小的时候，就会生成一个临时的文件存放到指定的临时目录里面。 3.创建文件上传解析器 4.监听文件上传进度
	 * 5.处理上传文件名乱码问题 6.判断提交的数据是否是上传表单的数据 7.是的话，则结束。 8.不是的话，则继续 9.设置上传单个文件的大小最大值
	 * 10.设置上传文件总量的最大值，最大值=同时上传多个文件的大小的最大值的和
	 * 11.使用servletFileUpload解析器上传数据，解析结果是一个List<FileItem>的集合，每个fileItem对应的一个form表单的输入项。
	 * 12.关于FileItem里面存的东西进行判断，如果是普通输入项数据，则直接获取名字getFieldName()和值getString("utf-8");处理乱码。
	 * 13.如果是上传文件的话：得到上传文件的名称。 14.得到扩展名 15.设置假名 16.设置保存路径 17.创建文件输出流 18.通过流将文件写入
	 * 19.关闭流 20.删除生成的临时文件 21.给出提示
	 */
	public boolean upLoadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = this.getAbsPath(TEMP_PATH);//创建临时目录
		FileUtil.createDiectorys(savePath);
		UploadHandler uploadHandler = new UploadHandler();// 上传文件工厂，创建临时文件夹
		request.setCharacterEncoding("utf-8");// 设置请求头解析为utf-8
		if (!ServletFileUpload.isMultipartContent(request)) {// 判断提交的请求是否是上传表单的数据,静态方法
			return false;
		}
		try {
			List<FileItem> fileItems = uploadHandler.getFileItems(request);	// 解析FileItem
			this.parseFileItems(fileItems);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void parseFileItems(List<FileItem> fileItems) throws IOException {
		for (FileItem fileItem : fileItems) {
			if (fileItem.isFormField()) {// 如果是普通的表单数据
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");// 解决普通输入项的数据的中文乱码问题
				System.out.println(name + value);
			} else {// 如果是上传文件的话
				String fileName = fileItem.getName();// 获得文件名
				if (fileName == null || "".equals(fileItem)) {// 如果为空结束处理这个文件
					continue;
				}
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);// 获取文件名
				String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);// 获得扩展名
				if (!FileUtil.checkFileSuffixs(supportFileSuffixs, fileName)) {// 判断上传的扩展名是否是支持的，不支持则结束
					continue;
				}
				String fileAlias = this.setFileAlias();// 设置文件假名，防止文件重名
				String filePath = this.createHashDirectory(fileAlias);// 设置目录防止同一目录文件过多
				FileBean fileBean = new FileBean(fileName, filePath, fileSuffix, fileAlias);// 存入对象
				fileBeans.add(fileBean);// 加入文件列表
				InputStream is = fileItem.getInputStream();// 存入文件
				String relativePath = fileBean.getFilePath();// 获取存储路径
				String savePath = this.getAbsPath(DIECTORY_PATH + File.separatorChar + relativePath);
				FileUtil.createDiectorys(savePath);// 创建路径
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
	 * 设置文件假名 文件假名包括：处理事件+1~100之间的随机数
	 */
	private String setFileAlias() {
		DateFormat dataFormat = new SimpleDateFormat("yyyyMMddhhmmss");// 设置输出格式
		Date date = new Date(System.currentTimeMillis()); // 获取现在时间
		String strDate = dataFormat.format(date); // 格式化输出现在时间
		int intRandom = (int) (1 + Math.random() * (100 - 1 + 1)); // 产生1~100的随机数
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
