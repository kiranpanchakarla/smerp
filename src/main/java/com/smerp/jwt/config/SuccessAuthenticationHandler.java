package com.smerp.jwt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.smerp.model.admin.User;





@Component
public class SuccessAuthenticationHandler implements AuthenticationSuccessHandler {

	
   @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
			    HttpSession session = request.getSession(); 
			    User user =   (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			    String redirect = ""; 
			    if(user != null){
			        session.setAttribute("username", user.getUserName());
			        if(((Authentication) user).getAuthorities().contains(new   SimpleGrantedAuthority("ROLE_ADMIN")) 
			                || ((Authentication) user).getAuthorities().contains(new   SimpleGrantedAuthority("ROLE_SUPER_ADMIN")))
			            redirect = "admin/"; 
			        else if(((Authentication)user).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_YOUR_ROLE")))
			            redirect = "yourrole/"; 
			    }
			    if(redirect.isEmpty())
			        redirect = "signin"; 

			    response.sendRedirect(redirect); 
	}

}
