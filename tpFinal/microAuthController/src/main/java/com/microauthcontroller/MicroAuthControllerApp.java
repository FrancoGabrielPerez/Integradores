package com.microauthcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Spring Boot application starter class
 * @Author Luciano Melluso, Franco Perez, Lautaro Liuzzi, Ruben Marchiori
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class MicroAuthControllerApp {
    public static void main(String[] args) {
        SpringApplication.run(MicroAuthControllerApp.class, args);
    }
}
