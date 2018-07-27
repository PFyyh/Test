package com.yyh.MyUtil;

public class MyString {
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String ...str){
			for(String s:str){
				if(null==s||"".equals(s)){
					return true;
				}
			}
			return false;
	}
	/**
	 * 用指定字符填充字符串
	 * places是保留位数，num是数字，character是填充字符
	 * @param places
	 * @param num
	 * @param character
	 * @return
	 */
	public static String characterFill(int places,int num,char character){
		return characterFill(places, num + "", character);
	}
	
	public static String characterFill(int places,String strTemp,char character){
		StringBuffer string = new StringBuffer();
		String str = null;
		for(int i = 0;i<places;i++ ){
			string.append(character);
		}
		string = string.append(strTemp);
		//裁剪掉数字的的长度，后面的就是补位的字符串
		str = string.substring(string.length() - places);
		return str;
	}
}
