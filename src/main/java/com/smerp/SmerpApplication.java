package com.smerp;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.smerp.filter.RequestContextFilter;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.smerp")
public class SmerpApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder  application) {
        return application.sources(SmerpApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SmerpApplication.class, args);
	}
	
	
	@Bean
	public FilterRegistrationBean greetingFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("request");
		registrationBean.setFilter(new RequestContextFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setEnabled(Boolean.TRUE);
		return registrationBean;
	}
	
	@Bean
    @Qualifier("fmConfig")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
        fmConfigFactoryBean.setTemplateLoaderPath("classpath:/emailtemplates/");
        return fmConfigFactoryBean;
    }
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@PostConstruct
	void start() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:30"));
	}
	 
}
