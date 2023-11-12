package com.microauthcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.microauthcontroller.MicroAuthControllerApp", "com.microAuthController.config"})
public class MicroAuthControllerApp {
    public static void main(String[] args) {
        SpringApplication.run(MicroAuthControllerApp.class, args);
    }
}
