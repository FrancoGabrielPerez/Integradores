package com.microtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * MicroTravelApp
 * 
 * Clase principal de la aplicacion.
 * @Author Luciano Melluso, Franco Perez, Lautaro Liuzzi, Ruben Marchiori
 */
@SpringBootApplication
@EnableJpaRepositories
public class MicroTravelApp {
	public static void main(String[] args) {
		SpringApplication.run(MicroTravelApp.class, args);
	}
}
