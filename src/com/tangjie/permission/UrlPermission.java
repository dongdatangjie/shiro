package com.tangjie.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

public class UrlPermission implements Permission{

	private String url;
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	
	public UrlPermission() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UrlPermission(String url) {
		// TODO Auto-generated constructor stub
		this.url=url;
	}
	
	@Override
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		if(!(p instanceof UrlPermission))return false;
		UrlPermission up=(UrlPermission)p;
		PatternMatcher matcher=new AntPathMatcher();
		System.out.println("----------------->"+this.getUrl()+"----"+up.getUrl()+"------"+matcher.matches(this.getUrl(), up.getUrl()));
		return matcher.matches(this.getUrl(), up.getUrl());
	}

}
