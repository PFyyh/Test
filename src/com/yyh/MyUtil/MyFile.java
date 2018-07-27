package com.yyh.MyUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
public class MyFile {
	/**
	 * 传入多个文件路径，判断是否存在返回，结果集
	 * @param fileUrl
	 * @return
	 */
	public static boolean[] fileExist(String ...fileUrl){
		boolean[] res = new boolean[fileUrl.length];
		for(int i=0;i<fileUrl.length;i++){
			res[i] = fileExist(fileUrl[i]);
		}
		return res;
	}
	/**
	 * 传入字符串，判断文件是否存在
	 * @param fileUrl
	 * @return
	 */
	public static Boolean fileExist(String fileUrl){
		File file = new File(fileUrl);
		return file.exists();
	}
	

	/**
	 * 树状显示文件目录
	 * @param str
	 * @param jj
	 */
	public static void showFiles(String str,int jj){
		if(!MyString.isEmpty(str)){
			File file = new File(str);
			File[] files = file.listFiles();
			if(files==null||files.length==0){
				return;
			}else{
				for(int i=0;i<files.length;i++){
					for(int k=0;k<jj;k++){
						System.out.print("\t");
					}
					System.out.println(files[i].getName());
					 if(files[i].isDirectory()){
						showFiles(files[i].getAbsolutePath(),jj+1);
					}
				}
			}
		}
	}
	/**
	 * 显示文件内容
	 * @param str
	 */
	public static void showFileContent(String str){
		if(!MyString.isEmpty(str)){
			File file = new File(str);
			FileReader fw = null;
			BufferedReader br = null;
			String str1;
			if(file.isFile()){
				try{
					fw = new FileReader(file);
					br = new BufferedReader(fw);
					while((str1=br.readLine())!=null){
						System.out.println(str1);
					}
					
				}catch (IOException e){
					e.printStackTrace();
				}finally{
					try {
						if(br!=null){
							br.close();
						}
						if(fw!=null){
							fw.close();
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	public static void copyFile(String strOldFile,String strNewFile) throws IOException{
		System.out.println("MyFile.copyFile()");
	
		if(!MyString.isEmpty(strOldFile,strNewFile)){
			
			File oldFile = new File(strOldFile);
			File newFile = new File(strNewFile);
			if(oldFile.isFile()){
				if(!newFile.exists()){
					
					newFile.createNewFile();
					
				} 
				Reader rd = new FileReader(oldFile);
				BufferedReader bf = new BufferedReader(rd);
				String str;
				Writer wt = new FileWriter(newFile,true);
				BufferedWriter bw = new BufferedWriter(wt);
				System.out.println();
				while((str=bf.readLine())!=null){
					
					bw.write(str);
					bw.newLine();
				}
				bw.flush();
				bf.close();
				bw.close();
				
			}else{
				System.out.println("false");
			}
		}
		
	}
	public static void binaryOpen() throws IOException{
		InputStream ist = new FileInputStream("E:/newFile.java");
		BufferedInputStream bit = new BufferedInputStream(ist);
		
		try {
			int length;
			
			bit.mark(0);
			//两种读取文件方式
			//1.通过byte数组来减少访问次数
			byte[] b = new byte[1024];
			while((length = bit.read(b))!=-1){
				System.out.println(new String(b));
			}

			bit.reset();
			
			//2.一个一个读
			while((length = bit.read())!=-1){
				System.out.print((char)length);
			}
			bit.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 字节转字符
	 * @throws FileNotFoundException
	 */
	public static void readOpen() throws FileNotFoundException{
		InputStream ist = new FileInputStream("E:/newFile.java");
		InputStreamReader isr = new InputStreamReader(ist);
		BufferedReader br = new BufferedReader(isr);
		String str;
		try {
			
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 拷贝文件夹
	 * @param oldDirectory
	 * @param newDirectory
	 * @throws IOException
	 */
	public static void copyDirectory(File oldDirectory,File newDirectory ) throws IOException{
		//如果存在
		if(oldDirectory.exists()){
			
			File temp;
			//如果是目录创建目录然后遍历递归
			if(oldDirectory.isDirectory()){
				//创建新的文件路径加上文件夹的名字
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.mkdir();
				String[] files = oldDirectory.list();
				for(int i = 0; i<files.length;i++){
					System.out.println("文件名："+files[i]);
					copyDirectory(new File(oldDirectory.getPath()+ File.separator + files[i]),new File(temp.getPath()));
				}
			}
			//如果是文件创建文件，复制文件
			if(oldDirectory.isFile()){
				//新的文件夹路径加上文件名
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.createNewFile();
				//复制文件内容
				copyFile(oldDirectory.getAbsolutePath(),temp.getAbsolutePath());
			}
		}
	}
}
