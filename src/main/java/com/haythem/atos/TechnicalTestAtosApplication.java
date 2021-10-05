package com.haythem.atos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author HJEBEL
 * 
 *         This is the main class using swagger configuration.
 * 
 *         To access to swagger Api Documentation:
 *         		http://localhost:8090/swagger-ui.html 
 *         To access to swagger Api docs:
 *         		http://localhost:8090/v2/api-docs
 *
 */
@SpringBootApplication
@EnableSwagger2
public class TechnicalTestAtosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestAtosApplication.class, args);
	}

}
