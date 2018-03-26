package com.qds.sa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableWebMvc
public class QdsApplication {

	private static final Logger logger = LoggerFactory.getLogger(QdsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(QdsApplication.class, args);
	}
}
