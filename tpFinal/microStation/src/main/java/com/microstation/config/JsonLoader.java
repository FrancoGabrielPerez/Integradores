package com.microstation.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microstation.model.StationMongo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
/*
@Component
public class JsonLoader {

	@Value("${mongodb.datafile}")
	private Resource dataFile;

	public List<StationMongo> loadStationsFromJson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(dataFile.getInputStream(), new TypeReference<List<StationMongo>>() {});
	}
}
*/