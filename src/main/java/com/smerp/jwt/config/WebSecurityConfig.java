package com.smerp.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	

	/*@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
*/
	/*@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}*/

/*	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}*/
	
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Override
	  protected void configure(
			  AuthenticationManagerBuilder auth) throws Exception {
		      auth.authenticationProvider(authProvider);
	}

	/*@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}*/

	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/resources/**","/forgotPass/**").permitAll()
            .antMatchers("/resources/**","/registration/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true)
            .failureUrl("/login?error=1").permitAll()
           .and().logout().permitAll();
    }
	

	
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/resources/**", "/").permitAll()
				.antMatchers("/login/**", "/signup").permitAll().antMatchers("/loginDetails/**", "/signup").permitAll()
				.anyRequest().authenticated().antMatchers("/admin/**").hasRole("ADMIN").and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler);
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class).formLogin()
				.loginPage("/login").defaultSuccessUrl("/loginDetails", true).permitAll().failureUrl("/login?error=1")
				.and().logout().permitAll();

	}*/

	 
}
