package com.tangjie.filter;

import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class MyPathMatchingFilter extends PathMatchingFilter{

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------------------------->Path Matching");
		System.out.println("--------------------->"+Arrays.toString((String[])mappedValue));
		return super.onPreHandle(request, response, mappedValue);
	}

}
