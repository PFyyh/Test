package com.yyh.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class FileUtil {
	/**
	 * 获取web-inf路径
	 * @return
	 */
	public static String absWEBINFPath(){
		String strClasses = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String path = strClasses.substring(0,strClasses.lastIndexOf("WEB-INF"));
		return path;
	}
	public static boolean checkFileSuffixs(String[] supportFileSuffixs,String fileName){
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (fileSuffix == null || "".equals(fileSuffix)) {
			throw new NullPointerException("文件名后缀为空");
		}
		for (String strTemp : supportFileSuffixs) {
			if (strTemp.equalsIgnoreCase(fileSuffix)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 创建目录
	 * @param filePath
	 */
	public static void createDiectorys(String filePath) {
		System.out.println("filePath"+filePath);
		File tempFile = new File(filePath);
		createDiectorys(tempFile);
	}
	/**
	 * 创建目录
	 * @param file
	 */
	public static void createDiectorys(File file){
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readFileContent(String fileName) throws IOException {
		File file = new File(fileName);
		return readFileContent(file.getName(), file.getPath());
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readFileContent(String fileName, String filePath) throws IOException {
		File file = new File(filePath, fileName);
		return readFileContent(file);

	}

	/**
	 * 读取文件内容
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readFileContent(File file) throws IOException {
		if (!file.exists()) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();// 存储文字内容
		BufferedReader br = new BufferedReader(new FileReader(file));
		String string = null;
		while ((string = br.readLine()) != null) {
			stringBuilder = stringBuilder.append(string);
		}
		br.close();
		return stringBuilder.toString();
	}

	/**
	 * 保存文件
	 * 
	 * @param is
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveFile(InputStream is, String fileName) throws IOException {
		System.out.println(fileName);
		if (is == null) {
			throw new NullPointerException("输入流空指针异常");
		}
		File file = new File(fileName);
		BufferedInputStream bis = new BufferedInputStream(is);
		FileOutputStream fot = new FileOutputStream(file);
		byte[] data = new byte[1024];
		int length = -1;
		while ((length = bis.read(data)) != -1) {
			fot.write(data, 0, length);
		}
		fot.close();
		bis.close();
	}

	/**
	 * 树状显示文件目录
	 * 
	 * @param str
	 * @param indent
	 */
	public static void showFiles(String str, int indent) {
		if (str != null && "".equals(str)) {
			File file = new File(str);
			File[] files = file.listFiles();
			if (files == null || files.length == 0) {
				return;
			} else {
				for (int i = 0; i < files.length; i++) {
					for (int k = 0; k < indent; k++) {
						System.out.print("\t");
					}
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						showFiles(files[i].getAbsolutePath(), indent + 1);
					}
				}
			}
		}
	}

	public static void copyFile(String strOldFile, String strNewFile) throws IOException {
		if (strOldFile != null && "".equals(strOldFile) && strNewFile != null && "".equals(strNewFile)) {
			File oldFile = new File(strOldFile);
			File newFile = new File(strNewFile);
			if (oldFile.isFile()) {
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				Reader rd = new FileReader(oldFile);
				BufferedReader bf = new BufferedReader(rd);
				String str;
				Writer wt = new FileWriter(newFile, true);
				BufferedWriter bw = new BufferedWriter(wt);
				System.out.println();
				while ((str = bf.readLine()) != null) {
					bw.write(str);
					bw.newLine();
				}
				bw.flush();
				bf.close();
				bw.close();

			} else {
				System.out.println("false");
			}
		}
	}

	public static void binaryOpen() throws IOException {
		InputStream ist = new FileInputStream("E:/newFile.java");
		BufferedInputStream bit = new BufferedInputStream(ist);
		try {
			int length;
			bit.mark(0);
			// 两种读取文件方式
			// 1.通过byte数组来减少访问次数
			byte[] b = new byte[1024];
			while ((length = bit.read(b)) != -1) {
				System.out.println(new String(b));
			}
			bit.reset();
			// 2.一个一个读
			while ((length = bit.read()) != -1) {
				System.out.print((char) length);
			}
			bit.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字节转字符
	 * 
	 * @throws FileNotFoundException
	 */
	public static void readOpen() throws FileNotFoundException {
		InputStream ist = new FileInputStream("E:/newFile.java");
		InputStreamReader isr = new InputStreamReader(ist);
		BufferedReader br = new BufferedReader(isr);
		String str;
		try {

			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 拷贝文件夹
	 * 
	 * @param oldDirectory
	 * @param newDirectory
	 * @throws IOException
	 */
	public static void copyDirectory(File oldDirectory, File newDirectory) throws IOException {
		// 如果存在
		if (oldDirectory.exists()) {

			File temp;
			// 如果是目录创建目录然后遍历递归
			if (oldDirectory.isDirectory()) {
				// 创建新的文件路径加上文件夹的名字
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.mkdir();
				String[] files = oldDirectory.list();
				for (int i = 0; i < files.length; i++) {
					System.out.println("文件名：" + files[i]);
					copyDirectory(new File(oldDirectory.getPath() + File.separator + files[i]),
							new File(temp.getPath()));
				}
			}
			// 如果是文件创建文件，复制文件
			if (oldDirectory.isFile()) {
				// 新的文件夹路径加上文件名
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.createNewFile();
				// 复制文件内容
				copyFile(oldDirectory.getAbsolutePath(), temp.getAbsolutePath());
			}
		}
	}
}
