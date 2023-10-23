package com.microtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MicroTravelApp {
	public static void main(String[] args) {
		SpringApplication.run(MicroTravelApp.class, args);
	}
}
