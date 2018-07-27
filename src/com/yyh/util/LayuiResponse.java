package com.yyh.util;

import java.util.List;

import net.sf.json.JSONObject;

public class LayuiResponse<E> {
	private int code;
	private String msg;
	private List<E> list;
	private int count;

	public LayuiResponse(List<E> list) {
		if (list == null) {
			code = 1;
			msg = "没有数据";
			count = 0;
		} else {
			code = 0;
			msg = "";
			this.list = list;
			count = list.size();
		}

	}

	public LayuiResponse(int code, String msg, List<E> list) {
		super();
		this.code = code;
		this.msg = msg;
		this.list = list;
		if (list == null) {
			count = 0;
		} else {
			count = list.size();
		}
	}

	@Override
	public String toString() {
		JSONObject userJSON = new JSONObject();
		userJSON.put("code", code);
		userJSON.put("msg", msg);
		userJSON.put("count", count);
		userJSON.put("data", list);
		System.out.println(list);
		return userJSON.toString();
	}
}
