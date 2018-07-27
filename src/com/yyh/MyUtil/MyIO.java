package com.yyh.MyUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class MyIO {
	/**
	 * �ֽ�����תdouble
	 * @param byteArr
	 * @return
	 * @throws IOException
	 */
	public static double byteArrToDouble(byte[] byteArr) throws IOException{
		//�����������������ڲ���
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(byteArr));
		double doubleNum = dis.readDouble();
		System.out.println("batd:"+doubleNum);
		dis.close();
		return doubleNum;
	}
	/**
	 * doubleת�ַ�����
	 * @param doubleNum
	 * @return
	 * @throws IOException
	 */
	public static byte[] doubleToByteArr(double doubleNum) throws IOException{
		byte[] byteArr = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeDouble(doubleNum);
		dos.flush();
		byteArr = bos.toByteArray();
		dos.close();
		return byteArr;
	}
	
	public static void closeAll(Closeable... io){
		for(Closeable temp:io){
			if(temp!=null){
				try {
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * ʹ�÷��ͷ���ʵ�ֹر�IO��
	 * @param io
	 */
	public static <T extends Closeable> void closeIO(T... io){
		for(Closeable temp:io){
			try {
				if (null != temp) {
					temp.close();
				}
			} catch (Exception e) {
			}
		}
	}
	public static  void closeSocket(ServerSocket socket){
		try {
			if (null != socket) {
				socket.close();
			}
		} catch (Exception e) {
		}
	}
	public static  void closeSocket(Socket socket){
		try {
			if (null != socket) {
				socket.close();
			}
		} catch (Exception e) {
		}
}
	public static  void closeSocket(DatagramSocket socket){
			try {
				if (null != socket) {
					socket.close();
				}
			} catch (Exception e) {
			}
	}
}
