package com.yyh.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CharactorEncodingUTF8
 */
public class CharactorEncoding implements Filter {


    public CharactorEncoding() {
    }

	public void destroy() {
		System.out.println("CharactorEncoding.destroy()");
	}

	/**
	 * ��������ͷ����Ϊutf-8������������⡣������ӦͷΪtext/html;charset=utf-8�����ı����ݣ�����Ϊutf-8
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println(url);
		if(url.endsWith(".do")){
			System.out.println("����do�ɹ�");
		}
		if(url.endsWith(".jsp")){
			System.out.println("����jsp�ɹ�");
		}
		String resource = url.substring(url.lastIndexOf("/"));
		System.out.println("resource:"+resource);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
