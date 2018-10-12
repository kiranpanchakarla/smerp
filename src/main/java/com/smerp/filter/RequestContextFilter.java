package com.smerp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import com.smerp.util.Context;
import com.smerp.util.RequestContext;


public class RequestContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Context ctx = new Context();
		RequestContext.set(ctx);
		// TODO Auto-generated method stub
		
		try {
			chain.doFilter(request, response);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			RequestContext.remove();
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
