package com.smerp.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String username = null;
		String authToken = null;
		boolean flag;

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("tokenId") != null) {
			authToken = (String) session.getAttribute("tokenId");
		}

		//logger.info("authToken---->" + authToken);
		if (authToken != null) {
			try {
				if(jwtTokenUtil.isTokenExpired(authToken)) {
					res.sendRedirect("/logout");
				}
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
				res.sendRedirect("/logout");
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
				res.sendRedirect("/logout");
			
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
				res.sendRedirect("/logout");
			} catch (Exception e) {
				logger.error("token expired", e);
				res.sendRedirect("/logout");
			}

		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		chain.doFilter(req, res);
	}
}