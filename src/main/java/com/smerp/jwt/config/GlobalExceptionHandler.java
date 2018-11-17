package com.smerp.jwt.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, 
               Exception exception) throws Exception {
		
		logger.error("[URL] : {}", req.getRequestURL(), exception);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("cause", exception.getCause());
		mav.addObject("message", exception.getMessage());
		mav.addObject("trace", exception.getStackTrace());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
