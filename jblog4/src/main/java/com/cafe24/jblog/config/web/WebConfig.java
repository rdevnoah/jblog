package com.cafe24.jblog.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({"com.cafe24.jblog.controller", "com.cafe24.jblog.exception"})
@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig {
}
