package com.hacra.cjtk.modules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 会计题库
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.hacra.cjtk"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
