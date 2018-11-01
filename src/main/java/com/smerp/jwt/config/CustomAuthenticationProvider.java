/*package com.smerp.jwt.config;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private TokenProvider tokenProvider;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		System.out.println("-------Inside Custom Authentication Provider-----------------");
		String username = auth.getName();
		String password = (String) auth.getCredentials();

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String serverName=request.getServerName();
		User user = userService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Invalid credentials. ", auth.getPrincipal()));
		} else if (!user.getEnabled()) {
			throw new UsernameNotFoundException(String.format("User is disabled ", user.getUsername()));
		}
		else if(user.getClient()!=null && user.getClient().getDomain()!=null) {
			if(!user.getClient().getDomain().getUrl().contains(serverName)) 
				throw new UsernameNotFoundException(String.format("Invalid credentials"));
		}

		if (!bcryptEncoder.encode(password).equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}
		String token = tokenProvider.generateToken(auth);
		
		System.out.println("token-------------->"+token);
		return new UsernamePasswordAuthenticationToken(user, null, getAuthority(user));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private Set<GrantedAuthority> getAuthority(User user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}


}
*/