package com.microadministration.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.dto.NewScooterDTO;
import com.microadministration.dto.ScooterDTO;
import com.microadministration.dto.ScooterReporteKilometrosDTO;
import com.microadministration.dto.StationDTO;
import com.microadministration.model.AdminStaff;
import com.microadministration.repository.AdminStaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service("adminService")
public class AdminService{
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();	

	@Transactional
	public ResponseEntity<?> saveNewScooter(NewScooterDTO scooterDTO) throws Exception {
		String stationUrl = "http://localhost:8002/monopatines/alta";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewScooterDTO> requestEntity = new HttpEntity<>(scooterDTO, headers);

        ResponseEntity<Void> response = restTemplate.exchange(stationUrl, HttpMethod.PUT, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al guardar el nuevo monopatin");
        }
		return response;
	}

	@Transactional
	public void deleteScooter(long scooterId) throws Exception {
		String stationUrl = "http://localhost:8002/monopatines/eliminar/" + scooterId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ScooterDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(stationUrl, HttpMethod.DELETE, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al borrar el monopatin " + scooterId);
		}
	}

	@Transactional
	public ResponseEntity<?> saveNewStation(StationDTO stationDTO) throws Exception {
		String stationUrl = "http://localhost:8001/estaciones/alta";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<StationDTO> requestEntity = new HttpEntity<>(stationDTO, headers);

		ResponseEntity<Void> response = restTemplate.exchange(stationUrl, HttpMethod.PUT, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al guardar la nueva estacion");
		}
		return response;
	}

	@Transactional
	public void deleteStation(long stationId) throws Exception {
		String accountUrl = "http://localhost:8001/estaciones/eliminar/" + stationId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StationDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(accountUrl, HttpMethod.DELETE, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error borrar la estacion " + stationId);
		}
	}

	@Transactional
	public void suspendAccount(long accountId) throws Exception {
		String accountUrl = "http://localhost:8003/cuentas/suspender/" + accountId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StationDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(accountUrl, HttpMethod.PUT, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error borrar la estacion " + accountId);
		}
	}

	@Transactional
	public List<ScooterReporteKilometrosDTO> getReportScootersByKms() throws Exception {
		String scooterUrl = "http://localhost:8002/monopatines/reporte/kilometros";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StationDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<ScooterReporteKilometrosDTO>> response = restTemplate.exchange(scooterUrl, 
								HttpMethod.GET, 
								requestEntity, 
								ParameterizedTypeReference.forType(List.class));
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al obtener los datos.");
		}
		return response.getBody();
		
	}
	
}
