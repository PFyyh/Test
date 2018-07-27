package com.yyh.MyUtil;

import java.io.UnsupportedEncodingException;

public class MyNet {
	/**
	 * 解决中文乱码问题
	 * @param value
	 * @param code
	 * @return
	 */
	public static String decode(String value,String code){
		try {
			return java.net.URLDecoder.decode(value,code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
