package com.smerp.jwt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smerp.jwt.config.TokenProvider;
import com.smerp.jwt.models.LoginUser;
import com.smerp.service.UserService;
import com.smerp.util.RequestContext;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	UserService userService;

	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) throws IOException {
		logger.info("login method"+error);
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorMsg", "Token Expired!");
			logger.error("Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("errorMsg", "You've been logged out successfully.");
		}
		
		
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = { "/loginDetails" }, method = RequestMethod.POST)
	public ModelAndView register(LoginUser loginUser, ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) throws AuthenticationException {
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = tokenProvider.generateToken(authentication);
			logger.info("generation of token" + token);
			logger.info("token is expired or not" + tokenProvider.getExpirationDateFromToken(token));
			HttpSession session = request.getSession(false);
			session.setAttribute("tokenId", token);
			modelAndView.setViewName("home");
			return modelAndView;
		} catch (Exception e) {
			logger.info("invalid credentials" + e.getMessage());
			modelAndView.addObject("errorMsg", "Username / Password was not found");
			modelAndView.setViewName("login");
			return modelAndView;
		}
	}

	/*@RequestMapping(value="/logoutDt", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		logger.info("logout method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}*/

	

}
