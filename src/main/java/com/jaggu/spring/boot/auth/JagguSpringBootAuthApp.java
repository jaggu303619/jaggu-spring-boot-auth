package com.jaggu.spring.boot.auth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JagguSpringBootAuthApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JagguSpringBootAuthApp.class)
		.properties("spring.config.name:JagguSpringBootAuthApp")
		.build()
		.run(args);		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.properties("spring.config.name:JagguSpringBootAuthApp")
				.sources(JagguSpringBootAuthApp.class);
	}
}
