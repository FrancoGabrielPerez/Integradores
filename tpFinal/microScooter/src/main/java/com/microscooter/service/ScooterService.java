package com.microscooter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import com.microscooter.dto.ScooterReporteKmsTiempoUsoDTO;
import com.microscooter.dto.ScooterReporteTiempoTotalDTO;
import com.microscooter.dto.ScooterReporteKilometrosDTO;
import com.microscooter.dto.ScooterReporteTiempoUsoDTO;
import com.microscooter.dto.StationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.microscooter.dto.InformeEstadoMonopatinesDTO;
import com.microscooter.dto.ScooterDTO;
import com.microscooter.model.Scooter;
import com.microscooter.repository.ScooterRepository;
import org.springframework.core.ParameterizedTypeReference;

/**
 * ScooterService
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Scooter.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service("scooterService")
public class ScooterService{
	@Autowired
	private ScooterRepository scooterRepository;

	@Autowired
	RestTemplate restTemplate = new RestTemplate();

	private static final String STATIONS_URL = "http://localhost:8001/estaciones";

	/**
	 * findAll
	 * Devuelve una lista de monopatines.
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ScooterDTO> findAll() {
		return this.scooterRepository.findAll().stream().map(ScooterDTO::new ).toList();
	}

	/**
	 * findById
	 * Devuelve un monopatin por id.
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public ScooterDTO findById(Long id) {
		return this.scooterRepository.findById(id).map(ScooterDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de monopatin invalido: " + id));
	}
	
	/**
	 * save
	 * Guarda un monopatin.
	 * @param entity
	 * @return
	 */
	@Transactional
	public ScooterDTO save(ScooterDTO entity) {
		return new ScooterDTO(this.scooterRepository.save(new Scooter(entity.getLatitud(), entity.getLongitud())));
	}

	/**
	 * delete
	 * Elimina un monopatin.
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		scooterRepository.delete(scooterRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de monopatin invalido: " + id)));
	}

	/**
	 * update
	 * Actualiza un monopatin.
	 * @param id
	 * @param entity
	 */
	@Transactional
	public void update(Long id, ScooterDTO entity) {
		Scooter scooter = scooterRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de monopatin invalido: " + id));
		scooter.setFromDTO(entity);
		scooterRepository.save(scooter);
	}

	/**
	 * findByKilometros
	 * Devuelve una lista de monopatines ordenados por kilometros.
	 * @return
	 */	
	@Transactional
	public List<ScooterReporteKilometrosDTO> findByKilometros(){
		return this.scooterRepository.findAllByOrderByKilometrosDesc().stream().map(ScooterReporteKilometrosDTO::new ).collect(Collectors.toList());
	}

	/**
	 * findByTiempoUso
	 * Devuelve una lista de monopatines ordenados por tiempo de uso.
	 * @return
	 */
	@Transactional
	public List<ScooterReporteTiempoUsoDTO> findByTiempoUso(){
		return this.scooterRepository.findAllByOrderByTiempoDeUsoDesc().stream().map(ScooterReporteTiempoUsoDTO::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
    public List<ScooterReporteKmsTiempoUsoDTO> findByKilometrosConTiempoUso() {
        List<ScooterDTO> scooters = scooterRepository.findAll().stream().map(ScooterDTO::new).collect(Collectors.toList());
		List<ScooterReporteKmsTiempoUsoDTO> scootersRes = new ArrayList<>();
		for (ScooterDTO scooter : scooters) {
			ScooterReporteKmsTiempoUsoDTO res = new ScooterReporteKmsTiempoUsoDTO(scooter);			
			scootersRes.add(res);
		}
		return scootersRes;
	}

	/**
	 * findByTiempoTotal
	 * Devuelve una lista de monopatines ordenados por tiempo total de uso.
	 * @return 
	 */
	@Transactional(readOnly = true)
	public List<ScooterReporteTiempoTotalDTO> findByTiempoTotal() {
		return this.scooterRepository.getTiempoTotal().stream().map(ScooterReporteTiempoTotalDTO::new).collect(Collectors.toList());
	}
    
	/**
	 * findOperativosMantenimiento
	 * Devuelve un informe de la cantidad de monopatines operativos y en mantenimiento.
	 * @return
	 */
	@Transactional(readOnly = true)
	public InformeEstadoMonopatinesDTO findOperativosMantenimiento(){
		return this.scooterRepository.getCantidadOperativosMantenimiento();
	}

	/**
	 * getScootersCercanos
	 * Devuelve una lista de monopatines cercanos a una latitud y longitud.
	 * @param latitud
	 * @param longitud
	 */
	@Transactional(readOnly = true)
	public List<Scooter> getScootersCercanos(Double latitud, Double longitud){
		List<Scooter> scooters = this.scooterRepository.findAll();
		List<Scooter> resultado = new ArrayList<Scooter>();
		for(Scooter s:scooters){
			if(s.calcularDistancia(latitud,longitud) <= 1){//mayor a 5 kilometros
				resultado.add(s);
			}
		}
		return resultado;
	}

	/**
	 * scooterEnEstacion
	 * Verifica sin un scooter se encuentra en una parada valida.
	 * @param scooterId
	 * @return
	 * @throws Exception
	 */
	public StationDTO scooterEnEstacion(long scooterId) throws Exception {
		ScooterDTO scooter = this.findById(scooterId);
		String estacionUrl = STATIONS_URL + "/verificar/latitud/" + scooter.getLatitud() +  "/longitud/" + scooter.getLongitud();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		HttpEntity<StationDTO> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<StationDTO> response = restTemplate.exchange(estacionUrl, 
									HttpMethod.GET, 
									requestEntity, 
									ParameterizedTypeReference.forType(StationDTO.class));
		switch (response.getStatusCode().value()) {
			case 200:
				return response.getBody();
			case 204:
				return null;
			default:
				throw new Exception("Error al obtener los datos." + response.getStatusCode() + response.getBody());
		}
	}
}
