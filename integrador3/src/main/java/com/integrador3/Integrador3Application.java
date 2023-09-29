package com.integrador3;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.integrador3.utils.DBHelper;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class Integrador3Application {
	@Autowired
	private DBHelper dbHelper;

	public static void main(String[] args) {
		SpringApplication.run(Integrador3Application.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		dbHelper.populateEstudiantesDB();
		dbHelper.populateCarrerasDB();
		dbHelper.populateEstudiantesCarrerasDB();
	}

}
