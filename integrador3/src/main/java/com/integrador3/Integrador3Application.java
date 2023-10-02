package com.integrador3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@ComponentScan("com.integrador3")
public class Integrador3Application {	
	public static void main(String[] args) {
		SpringApplication.run(Integrador3Application.class, args);
	}
}
