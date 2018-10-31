package com.smerp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	//	System.out.println("hiiii-----------iiiiiiiiii");
		
		HttpServletRequest 	req=(HttpServletRequest) request; 
		
		String contextPath = req.getContextPath();
		
		
		try {
			chain.doFilter(request, response);
		}catch(Exception e){
			
			/* ErrorResponse errorResponse = new ErrorResponse("token expired","401");
			 HttpServletResponse res=(HttpServletResponse) response;
			 res.getWriter().write(convertObjectToJson(errorResponse));*/
		} finally {
			//RequestContext.remove();
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
}
