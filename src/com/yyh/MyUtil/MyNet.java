package com.yyh.MyUtil;

import java.io.UnsupportedEncodingException;

public class MyNet {
	/**
	 * ���������������
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
