package com.microadministration.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.microadministration.dto.ScooterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service("maintenanceService")
public class MaintenanceService{

	private RestTemplate restTemplate;	

	@Transactional
	public void updateScooterState(long idScooter, String estado) {
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/buscar/" + idScooter, ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de scooter invalido: " + idScooter);
		}
		scooter.getBody().setEstado(estado);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ScooterDTO scooterDTO = scooter.getBody();
		HttpEntity<ScooterDTO> requestEntity = new HttpEntity<>(scooterDTO, headers);

		try {
			ResponseEntity<Void> response = restTemplate.exchange("http://localhost:8002/monopatines/actualizar/", HttpMethod.PUT, requestEntity, Void.class);
			if (response.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error al actualizar el estado del monopatin" + idScooter);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar el estado del monopatin. ", e);
		}
	}

		
	
}
