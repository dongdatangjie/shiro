package com.tangjie.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static WebApplicationContext wc;
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		wc=WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		System.out.println("-----------------系统初始化成功-------------");
	}
	
	public static WebApplicationContext getWc(){
		return wc;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
