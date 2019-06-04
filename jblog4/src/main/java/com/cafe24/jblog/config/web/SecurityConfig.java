package com.cafe24.jblog.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.security.AdminInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;

@EnableWebMvc
@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter{


	
	/*
	 * Interceptor
	 * 
	 * 
	 */
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
		 registry
		 .addInterceptor(authLoginInterceptor())
		 .addPathPatterns("/user/auth");
		 
		 registry
		 .addInterceptor(authLogoutInterceptor())
		 .addPathPatterns("/user/logout");
		 
		 registry
		 .addInterceptor(adminInterceptor())
		 .addPathPatterns("/*/admin/**")
		 .addPathPatterns("//admin/**");
		 
		 
	 }
	 
	 @Bean
	 AuthLoginInterceptor authLoginInterceptor() {
		 return new AuthLoginInterceptor();
	 }
	 
	 @Bean
	 AuthLogoutInterceptor authLogoutInterceptor() {
		 return new AuthLogoutInterceptor();
	 }
	 
	 @Bean
	 AdminInterceptor adminInterceptor() {
		 return new AdminInterceptor();
	 }
	 
	 
	 
}
