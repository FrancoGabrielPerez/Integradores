package com.microadministration.service;

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
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();	

	@Transactional
	public void updateScooterState(long idScooter, String estado) {
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/buscar/" + idScooter, ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de scooter invalido: " + idScooter);
		}
		
		HttpEntity<ScooterDTO> requestEntity;
		ScooterDTO scooterBody = scooter.getBody();
		if (scooterBody != null) {
			scooterBody.setEstado(estado);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			requestEntity = new HttpEntity<>(scooterBody, headers);
		} else {
			throw new RuntimeException("Error al actualizar el estado del monopatin. El cuerpo de la respuesta es nulo.");
		}

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
