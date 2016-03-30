package com.vlotar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application launcher
 */
@SpringBootApplication
public class JavaLogAggregationApplication {

	public static void main(String[] args) {
		//starts spring application (embedded tomcat, security, logging etc.)
		SpringApplication.run(JavaLogAggregationApplication.class, args);
	}
}
