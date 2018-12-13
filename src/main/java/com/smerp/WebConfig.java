package com.smerp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/data/Company/logo/**").addResourceLocations("file:///D:/data/Company/logo/");
	       registry.addResourceHandler("/data/Company/users/**").addResourceLocations("file:///D:/data/Company/users/");
     
	 }
}
