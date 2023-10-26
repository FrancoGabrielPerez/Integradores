package com.microscooter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microscooter.dto.ScooterDTO;
import com.microscooter.model.Scooter;
import com.microscooter.repository.ScooterRepository;


@Service("scooterService")
public class ScooterService{
	@Autowired
	private ScooterRepository scooterRepository;

	@Transactional(readOnly = true)
	public List<ScooterDTO> findAll() {
		return this.scooterRepository.findAll().stream().map(ScooterDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public ScooterDTO findById(Long id) {
		return this.scooterRepository.findById(id).map(ScooterDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
	}
	
	@Transactional
	public ScooterDTO save(ScooterDTO entity) {
		return new ScooterDTO(this.scooterRepository.save(new Scooter(entity)));
	}

	@Transactional
	public void delete(Long id) {
		scooterRepository.delete(scooterRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id)));
	}

	@Transactional
	public void update(Long id, ScooterDTO entity) {
		Scooter scooter = scooterRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
		scooter.setFromDTO(entity);
		scooterRepository.save(scooter);
	}
	
	
	/*
	@Transactional(readOnly = true)
	public List<InformeScooterDTO> informeScooters() {
		return this.inscriptos.informeScooters();
	}

	@Transactional(readOnly = true)
	public List<InformeScooterCantEstudiantesDTO> scootersOrdenadas() {
		return this.scooterRepository.scootersOrdenadas();
	} */
}
