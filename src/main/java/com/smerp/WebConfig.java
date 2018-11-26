package com.smerp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/SMERP/Company/logo/**").addResourceLocations("file:/SMERP/Company/logo/");
	       registry.addResourceHandler("/SMERP/Company/users/**").addResourceLocations("file:/SMERP/Company/users/");
       
	 }
}
