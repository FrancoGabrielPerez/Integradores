package com.microscooter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.microscooter.dto.ScooterReporteKmsTiempoUsoDTO;
import com.microscooter.dto.ScooterReporteKilometrosDTO;
import com.microscooter.dto.ScooterReporteTiempoUsoDTO;
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
import java.util.stream.Collectors;

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

	@Transactional
	public List<ScooterReporteKilometrosDTO> findByKilometros(){
		return this.scooterRepository.findAllByOrderByKilometrosDesc().stream().map(ScooterReporteKilometrosDTO::new ).collect(Collectors.toList());
	}

	@Transactional
	public List<ScooterReporteTiempoUsoDTO> findByTiempoUso(){
		return this.scooterRepository.findAllByOrderByTiempoDeUsoDesc().stream().map(ScooterReporteTiempoUsoDTO::new).collect(Collectors.toList());
	}
	

    public List<ScooterReporteKmsTiempoUsoDTO> findByKilometrosConTiempoUso() {
        List<ScooterDTO> scooters = scooterRepository.findAll().stream().map(ScooterDTO::new).collect(Collectors.toList());
		List<ScooterReporteKmsTiempoUsoDTO> scootersRes = new ArrayList<>();
		for (ScooterDTO scooter : scooters) {
			ScooterReporteKmsTiempoUsoDTO res = new ScooterReporteKmsTiempoUsoDTO(scooter);			
			scootersRes.add(res);
		}
		return scootersRes;
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
