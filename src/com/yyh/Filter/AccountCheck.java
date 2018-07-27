package com.yyh.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet Filter implementation class AccountCheck
 */
public class AccountCheck implements Filter {

	/**
	 * Default constructor.
	 */
	public AccountCheck() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AccountCheck.doFilter()");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		String urlFile;
		if (url.indexOf('?') > 0) {
			urlFile = url.substring(url.lastIndexOf("/"), url.lastIndexOf("?"));
		} else {
			urlFile = url.substring(url.lastIndexOf("/"));
		}

		HttpSession session = req.getSession();
		String account = (String) session.getAttribute("account");
		if ("/login.jsp".equals(urlFile)) {
			chain.doFilter(request, response);
			return;
		} else {
			if (account == null || "".equals(account)) {
				// 跳转到登陆页面
				res.sendRedirect("/PEsystem/page/login/login.jsp");
				return;
			} else {
				// 已经登陆,继续此次请求
				chain.doFilter(request, response);
				return;
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
