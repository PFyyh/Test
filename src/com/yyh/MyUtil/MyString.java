package com.yyh.MyUtil;

public class MyString {
	/**
	 * �ж��Ƿ�Ϊ��
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
	 * ��ָ���ַ�����ַ���
	 * places�Ǳ���λ����num�����֣�character������ַ�
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
		//�ü������ֵĵĳ��ȣ�����ľ��ǲ�λ���ַ���
		str = string.substring(string.length() - places);
		return str;
	}
}
