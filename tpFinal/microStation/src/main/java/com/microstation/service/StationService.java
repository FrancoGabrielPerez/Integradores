package com.microstation.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microstation.dto.StationDTO;
//import com.microstation.model.Station;
import com.microstation.model.StationMongo;
import com.microstation.repository.StationMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * StationService
 * 
 * Clase que contiene los metodos de acceso a la base de datos.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Service("stationService")
public class StationService{
	@Autowired
	private StationMongoRepository stationMongoRepository;
	
	/**
	 * findAll
	 * @return List<StationDTO>
	 */
	@Transactional(readOnly = true)
	public List<StationDTO> findAll() {
		return this.stationMongoRepository.findAll().stream().map(StationDTO::new ).toList();
	}

	/**
	 * findById
	 * @param id
	 * @return StationDTO
	 */
	@Transactional(readOnly = true)
	public StationDTO findById(String id) {
		return this.stationMongoRepository.findById(id).map(StationDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
	}
	
	/**
	 * save
	 * @param entity
	 * @return StationDTO
	 */
	@Transactional
	public StationDTO save(StationDTO entity) {
		return new StationDTO(this.stationMongoRepository.save(new StationMongo(entity)));
	}

	@Transactional
	public void delete(String id){
		stationMongoRepository.delete(stationMongoRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id)));
	}

	/**
	 * update
	 * @param id
	 * @param entity
	 * @throws Exception
	 */
	@Transactional
	public void update(String id, StationDTO entity) throws Exception{
		StationMongo station = stationMongoRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
		station.setLatitud(entity.getLatitud());
		station.setLongitud(entity.getLongitud());
		station.setName(entity.getName());
		stationMongoRepository.save(station);
	}
	
	/**
	 * findByLatitudAndLongitud
	 * @param latitud
	 * @param longitud
	 * @return StationDTO
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public StationDTO findByLatitudAndLongitud(String latitud, String longitud) throws Exception{
		StationMongo station = this.stationMongoRepository.findByLatitudAndLongitud(latitud, longitud);
		StationDTO stationDTO = new StationDTO(station);
	 // Copia los atributos de Station a StationDTOBeanUtils.copyProperties(station, stationDTO); // Copia los atributos de Station a StationDTO
		return stationDTO;
	}

}
