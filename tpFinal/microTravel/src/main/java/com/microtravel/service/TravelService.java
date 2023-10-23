package com.microtravel.service;

import java.net.URI;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.microtravel.dto.UserDTO;
import com.microtravel.dto.TravelDTO;
import com.microtravel.model.Travel;
import com.microtravel.repository.TravelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service("travelService")
public class TravelService{
	@Autowired
	private TravelRepository travelRepository;
	@Autowired
	private FareRepository fareRepository;

	private RestTemplate restTemplate;

	@Transactional(readOnly = true)
	public List<TravelDTO> findAll() {
		return this.travelRepository.findAll().stream().map(TravelDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public TravelDTO findById(Long id) {
		return this.travelRepository.findById(id).map(TravelDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
	}
	
	@Transactional
	public TravelDTO save(long idUsuario, long idScooter) {
		ResponseEntity<UserDTO> user = restTemplate.getForEntity("http://localhost:8080/usuarios/buscar/" + idUsuario, UserDTO.class);
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/scooters/buscar/" + idScooter, ScooterDTO.class);
		if (user.getStatusCode() != HttpStatus.OK || scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de usuario o scooter invalido: " + idUsuario + " " + idScooter);
		}
		TravelDTO res = new TravelDTO(this.travelRepository.save(new Travel(idUsuario, idScooter, getCurrentFare())));
		scooter.getBody().setEstado("Ocupado");
		ResponseEntity<?> scooterResponse = restTemplate.postForLocation("http://localhost:8002/scooters/actualizar", scooter.getBody());
		return res;
	}

	private Double getCurrentFare() {
		return null;
	}

	@Transactional
	public void delete(Long id) {
		travelRepository.delete(travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id)));
	}

	@Transactional
	public void update(Long id, TravelDTO entity) {
		Travel travel = travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
		travel.setLatitud(entity.getLatitud());
		travel.setLongitud(entity.getLongitud());
		travelRepository.save(travel);
	}
	
	
	/*
	@Transactional(readOnly = true)
	public List<InformeTravelDTO> informeTravels() {
		return this.inscriptos.informeTravels();
	}

	@Transactional(readOnly = true)
	public List<InformeTravelCantEstudiantesDTO> travelsOrdenadas() {
		return this.travelRepository.travelsOrdenadas();
	} */
}
