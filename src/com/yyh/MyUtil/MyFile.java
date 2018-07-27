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
	 * �������ļ�·�����ж��Ƿ���ڷ��أ������
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
	 * �����ַ������ж��ļ��Ƿ����
	 * @param fileUrl
	 * @return
	 */
	public static Boolean fileExist(String fileUrl){
		File file = new File(fileUrl);
		return file.exists();
	}
	

	/**
	 * ��״��ʾ�ļ�Ŀ¼
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
	 * ��ʾ�ļ�����
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
			//���ֶ�ȡ�ļ���ʽ
			//1.ͨ��byte���������ٷ��ʴ���
			byte[] b = new byte[1024];
			while((length = bit.read(b))!=-1){
				System.out.println(new String(b));
			}

			bit.reset();
			
			//2.һ��һ����
			while((length = bit.read())!=-1){
				System.out.print((char)length);
			}
			bit.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * �ֽ�ת�ַ�
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
	 * �����ļ���
	 * @param oldDirectory
	 * @param newDirectory
	 * @throws IOException
	 */
	public static void copyDirectory(File oldDirectory,File newDirectory ) throws IOException{
		//�������
		if(oldDirectory.exists()){
			
			File temp;
			//�����Ŀ¼����Ŀ¼Ȼ������ݹ�
			if(oldDirectory.isDirectory()){
				//�����µ��ļ�·�������ļ��е�����
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.mkdir();
				String[] files = oldDirectory.list();
				for(int i = 0; i<files.length;i++){
					System.out.println("�ļ�����"+files[i]);
					copyDirectory(new File(oldDirectory.getPath()+ File.separator + files[i]),new File(temp.getPath()));
				}
			}
			//������ļ������ļ��������ļ�
			if(oldDirectory.isFile()){
				//�µ��ļ���·�������ļ���
				temp = new File(newDirectory.getPath() + File.separator + oldDirectory.getName());
				temp.createNewFile();
				//�����ļ�����
				copyFile(oldDirectory.getAbsolutePath(),temp.getAbsolutePath());
			}
		}
	}
}
