package com.microsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
public class SecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }
}
