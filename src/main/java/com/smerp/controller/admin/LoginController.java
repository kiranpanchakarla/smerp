package com.smerp.controller.admin;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smerp.jwt.config.TokenProvider;
import com.smerp.model.admin.User;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);
	

	@Autowired
	TokenProvider  tokenProvider;

	

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletResponse response) throws IOException {

		logger.error("Invalid username and password!");
		
		if (response!=null) {
			logger.error("Invalid username and password!"+response.getStatus());
		}
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorMsg","Invalid username or password");
			logger.error("Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("errorMsg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpSession session) {
		
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		String token=tokenProvider.generateToken(auth);
		
		session.setAttribute("tokenId", token);
		
		try {
			User user=(User) auth.getPrincipal();
			
			logger.info("company details----------"+user.getCompany());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return "home";
	}
	
/*	
	@RequestMapping("/tokenExpired")
	public String tokenExpired() {
		logger.info("tokenExpired");
		return "redirect:login";

	}*/
	
}
