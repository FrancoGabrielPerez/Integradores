package com.microstation.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microstation.dto.StationDTO;
import com.microstation.model.StationMongo;
import com.microstation.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

	@Value("${mongodb.datafile}")
	private Resource dataFile;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void run(ApplicationArguments args) throws IOException {
		if (!mongoTemplate.collectionExists(StationMongo.class)) {
			ObjectMapper objectMapper = new ObjectMapper();
			List<StationMongo> stations = objectMapper.readValue(dataFile.getInputStream(), new TypeReference<List<StationMongo>>() {
			});
			mongoTemplate.insertAll(stations);
		}
	}

//	@Override
//	public void run(ApplicationArguments args) {
//		try {
//			List<StationMongo> stations = jsonLoader.loadStationsFromJson();
//			stations.forEach(station -> service.save(new StationDTO(station)));
//		} catch (IOException e) {
//			e.printStackTrace();
//			// Manejar la excepción apropiadamente
//		}
//	}
}