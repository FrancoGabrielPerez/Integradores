package com.microadministration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MicroAdministrationApp {
	public static void main(String[] args) {
		SpringApplication.run(MicroAdministrationApp.class, args);
	}
}
