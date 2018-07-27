package com.yyh.MyUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StyleChange {
	/**
	 * double转字节数组
	 * @param doubleNum
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] doubleToByteArr(double doubleNum) throws UnsupportedEncodingException{
		byte[] arr = null;
		arr =new String(String.valueOf(doubleNum)).getBytes();
		return arr;
	}
	/**
	 * double转字节数组，按照指定的charset字符集
	 * @param doubleNum
	 * @param charset
	 * @return
	 */
	public static byte[] doubleToByteArr(double doubleNum,Charset charset){
		byte[] arr = null;
		arr =new String(String.valueOf(doubleNum)).getBytes(charset);
		return arr;
	}
	/**
	 * 字节数组转double
	 * @param byteArr
	 * @return
	 */
	public static double byteArrToDouble(byte[] byteArr){
		double doubleNum = 0;
		doubleNum = Double.parseDouble(new String(byteArr));
		return doubleNum;
	}
}
