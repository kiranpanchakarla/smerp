package com.smerp.jwt.config;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.smerp.controller.admin.LoginController;
import com.smerp.model.admin.Role;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	

	@SuppressWarnings("unused")
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String password = (String) auth.getCredentials();
		logger.info("user name" + auth.getName());
		logger.info("user name" + password);
		User user = userService.findOne(auth.getName());

		logger.info("user details " + user);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Invalid credentials. ", auth.getPrincipal()));
		} else if (!user.getEnabled()) {
			throw new UsernameNotFoundException(String.format("User is disabled ", user.getUsername()));
		}
		
		logger.info("from db password " + user.getPassword().trim());
		if (!bcryptEncoder.matches(password, user.getPassword().trim())) {
			throw new BadCredentialsException("Wrong password.");
		}
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getAuthority(user));
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.add(authority);
		return new UsernamePasswordAuthenticationToken(user, null, updatedAuthorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public static List<GrantedAuthority> createAuthorityList(String... roles) {
		List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}

	private String getAuthority(User user) {
		Set<Role> rolesSet = user.getRoles();
		String roleData = null;

		for (Role roleObj : rolesSet) {
			roleData = roleObj.getName();
		}
		/*Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});*/
		return roleData;
	}

}
