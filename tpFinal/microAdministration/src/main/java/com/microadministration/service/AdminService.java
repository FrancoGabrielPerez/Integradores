package com.microadministration.service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.microadministration.dto.ScooterDTO;
import com.microadministration.dto.TravelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * AdminService
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Administracion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Service("adminService")
public class AdminService{
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();	

	private static final String SCOOTERS_URL = "http://localhost:8002/monopatines";

    private static final String TRAVELS_URL = "http://localhost:8003/viajes";

	/**
	 * getScootersWithMoreTravelsInYear
	 * Obtiene los monopatines que hicieron X cantidad de viajes en determinado año.
	 * @param travelQuantity
	 * @param year
	 * @return ResponseEntity<?>
	*/
	@Transactional(readOnly = true)
	public Object getScootersWithMoreTravelsInYear(Long travelQuantity, Integer year, String token) throws Exception {
		String travelUrl = TRAVELS_URL;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		HttpEntity<TravelDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<TravelDTO>> response = restTemplate.exchange(travelUrl, 
								HttpMethod.GET, 
								requestEntity, 
								new ParameterizedTypeReference<List<TravelDTO>>() {});
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al obtener los datos.");
		}
		List<TravelDTO> travels = response.getBody();
		if (travels == null) {
			throw new Exception("Error al obtener los datos.");
		}
		List<ScooterDTO> filteredScooters = travels.stream()
		.filter((travel) -> {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(travel.getEndTime().getTime());
			return calendar.get(Calendar.YEAR) == year;
		})
		.collect(Collectors.groupingBy(TravelDTO::getScooterId, Collectors.counting()))
		.entrySet().stream()
		.filter(entry -> entry.getValue() > travelQuantity)
		.map(entry -> {
			String scooterUrl = SCOOTERS_URL + entry.getKey();
			HttpEntity<ScooterDTO> requestEntity2 = new HttpEntity<>(headers);
			ResponseEntity<ScooterDTO> response2 = restTemplate.exchange(scooterUrl,
					HttpMethod.GET,
					requestEntity2,
					ParameterizedTypeReference.forType(ScooterDTO.class));
			if (response2.getStatusCode() != HttpStatus.OK) {
				throw new IllegalArgumentException("Error al obtener los datos.");
			}
			return response2.getBody();
		})
		.filter(Objects::nonNull)  // Remove null responses
		.collect(Collectors.toList());
		return filteredScooters;
	}
}
