package com.cafe24.jblog.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({DBConfig.class, MyBatisConfig.class})
@ComponentScan({"com.cafe24.jblog.service", "com.cafe24.jblog.repository"})
public class AppConfig {
	
}
