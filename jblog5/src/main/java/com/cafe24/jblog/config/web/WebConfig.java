package com.cafe24.jblog.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.security.AdminInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
				.modulesToInstall(new ParameterNamesModule());
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json",Charset.forName("UTF-8"))));
		
		return converter;
	}
	
	
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html",Charset.forName("UTF-8"))));
		return converter;
	}
	/*
	 *  Message Converter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
	}
	
	
	
	
	 
	 @Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/assets/jblog/images/**").addResourceLocations("file:/usr/local/cafe24/jblog/images/");
		}
	
	
	/*
	 * Interceptor
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
