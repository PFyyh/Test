package com.util.page;

public class IndexOutOfPageException extends Exception {

	private static final long serialVersionUID = -950931260109259796L;

	IndexOutOfPageException(String msg){
		super(msg);
	}
}
