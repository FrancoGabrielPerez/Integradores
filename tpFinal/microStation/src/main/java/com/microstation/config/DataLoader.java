package com.microstation.config;

import com.microstation.dto.StationDTO;
import com.microstation.model.StationMongo;
import com.microstation.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
/*
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private JsonLoader jsonLoader;

	@Autowired
	private StationService service;

	@Override
	public void run(ApplicationArguments args) {
		try {
			List<StationMongo> stations = jsonLoader.loadStationsFromJson();
			stations.forEach(station -> service.save(new StationDTO(station)));
		} catch (IOException e) {
			e.printStackTrace();
			// Manejar la excepción apropiadamente
		}
	}
}
*/