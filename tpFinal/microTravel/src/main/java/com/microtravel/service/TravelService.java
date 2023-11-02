package com.microtravel.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.microtravel.dto.UserDTO;
import com.microtravel.dto.AccountDTO;
import com.microtravel.dto.FareDTO;
import com.microtravel.dto.NewBillDTO;
import com.microtravel.dto.TravelDTO;
import com.microtravel.dto.ScooterDTO;
import com.microtravel.dto.StationDTO;
import com.microtravel.model.Fare;
import com.microtravel.model.Travel;
import com.microtravel.repository.FareRepository;
import com.microtravel.repository.TravelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service("travelService")
public class TravelService{
	public static final int TIEMPOLIMITE = 15;

	@Autowired
	private TravelRepository travelRepository;
	
	@Autowired
	private FareRepository fareRepository;

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	@Transactional(readOnly = true)
	public List<TravelDTO> findAll() {
		return this.travelRepository.findAll().stream().map(TravelDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public TravelDTO findById(Long id) {
		return this.travelRepository.findById(id).map(TravelDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id));
	}
	
	@Transactional
	public TravelDTO save(long idUsuario, long idScooter) throws IllegalArgumentException, RestClientException {
		ResponseEntity<UserDTO> user = restTemplate.getForEntity("http://localhost:8004/usuarios/buscar/" + idUsuario, UserDTO.class);
		if (user.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de usuario invalido: " + idUsuario);
		}
		ResponseEntity<List<AccountDTO>> accounts = restTemplate.exchange("http://localhost:8004/cuentas/usuario/" + idUsuario, 
											HttpMethod.GET, null, new ParameterizedTypeReference<List<AccountDTO>>() {});
		if (accounts.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("Error al obtener las cuentas del usuario: " + idUsuario);
		}
		boolean hasCredit = false;
		for(AccountDTO account : Objects.requireNonNull(accounts.getBody())) {
			if (account.isHabilitada() && account.getSaldo() > 0)
			hasCredit = true;
		}
		if (!hasCredit) {
			throw new IllegalArgumentException("El usuario no tiene saldo suficiente para realizar un viaje");
		}
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/" + idScooter, ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + idScooter);
		}
		ScooterDTO scooterBody = scooter.getBody();
		if (scooterBody == null) {
			throw new IllegalArgumentException("El cuerpo de la respuesta del monopatin es nulo");
		}
		if (scooterBody.getEstado().equals("ocupado")) {
			throw new IllegalArgumentException("El monopatin ya se encuentra en uso");
		}
		if (!updateScooterState(idScooter, scooterBody, "ocupado")) {
			throw new IllegalArgumentException("El estado del monopatin no se pudo actualizar");
		}
		Integer scooterTiempoDeUso = scooterBody.getTiempoDeUso();
		Double scooterKilometros = scooterBody.getKilometros();
		Integer scooterTiempoEnpausa = scooterBody.getTiempoEnpausa();
		TravelDTO res = new TravelDTO(this.travelRepository.save(new Travel(idUsuario, idScooter, -scooterTiempoEnpausa, getCurrentFlatFare(), -scooterTiempoDeUso, -scooterKilometros)));
		
		return res;
	}

	private boolean updateScooterState(long idScooter, ScooterDTO scooter, String estado) {
		String scooterUpdateUrl = "http://localhost:8002/monopatines/actualizar/" + idScooter;
		scooter.setEstado(estado);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ScooterDTO> requestEntity = new HttpEntity<>(scooter, headers);
		ResponseEntity<?> scooterResponse = restTemplate.exchange(
			scooterUpdateUrl,
			HttpMethod.PUT,
			requestEntity,
			String.class
			);
		return scooterResponse.getStatusCode() == HttpStatus.OK;
	}

	@Transactional(readOnly = true)
	private Double getCurrentFlatFare() {
		return fareRepository.findFirstFlatRate();
	}

	@Transactional(readOnly = true)
	private Double getCurrentFullRate() {
		return fareRepository.findFirstFullRate();
	}

	@Transactional
	public void travelEnd(Long id) throws Exception {
		Travel travel = travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("No se encuentra el viaje con id: " + id));
		if (travel.getEndTime() != null) {
			throw new IllegalArgumentException("El viaje ya esta finalizado");
		}
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/" + travel.getScooterId(), ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + travel.getScooterId());
		}
		ScooterDTO scooterBody = scooter.getBody();
		if (scooterBody == null) {
			throw new IllegalArgumentException("El cuerpo de la respuesta del monopatin es nulo");
		}
		addRandomTravelData(scooterBody); //simula datos de viaje
		String scooterLatitudStr = scooterBody.getLatitud();
		String scooterLongitudStr = scooterBody.getLongitud();
		if (scooterLatitudStr == null || scooterLongitudStr == null) {
			throw new IllegalArgumentException("La latitud o longitud del monopatin es nula");
		}
		Double scooterLatitud = Double.parseDouble(scooterLatitudStr);
		Double scooterLongitud = Double.parseDouble(scooterLongitudStr);
		ResponseEntity<StationDTO> station = restTemplate.getForEntity("http://localhost:8001/estaciones/verificar/latitud/" + scooterLatitud + "/longitud/" + scooterLongitud, StationDTO.class);
		if (station.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("No se encuentra una estacion valida para el monopatin: " + travel.getScooterId());
		}
		Integer scooterTiempoEnpausa = scooterBody.getTiempoEnpausa();
		Integer scooterTiempoDeUso = scooterBody.getTiempoDeUso();
		Double scooterKilometros = scooterBody.getKilometros();
		if (scooterTiempoEnpausa == null || scooterTiempoDeUso == null || scooterKilometros == null) {
			throw new IllegalArgumentException("El tiempo en pausa, el tiempo de uso o los kilometros del monopatin son nulos");
		}
		travel.setPauseTime(travel.getPauseTime() + scooterTiempoEnpausa);
		travel.setUseTime(travel.getUseTime() + scooterTiempoDeUso);
		travel.setKilometers(travel.getKilometers() + scooterKilometros);
		travel.setEndTime(new Timestamp(System.currentTimeMillis()));
		if (travel.getPauseTime() < TIEMPOLIMITE) {
			travel.setFare(travel.getUseTime() * getCurrentFlatFare());
		}
		else {
			travel.setFare(travel.getUseTime() * getCurrentFullRate());
		}	
			
		travelRepository.save(travel);
		updateScooterState(travel.getScooterId(), scooter.getBody(), "disponible");
		updateUserAccount(travel.getUserId(), travel.getFare());
		sendBill(travel);
	}

	//Simula datos de viaje
	private void addRandomTravelData(ScooterDTO scooterBody) {
		scooterBody.setTiempoDeUso(scooterBody.getTiempoDeUso() + (int) (Math.random() * 20));
		scooterBody.setTiempoEnpausa(scooterBody.getTiempoEnpausa() + (int) (Math.random() * 15));
		scooterBody.setKilometros(scooterBody.getKilometros() + Math.random() * 100);
	}

	@Transactional
	public void sendBill(Travel travel) throws Exception {
		String accountUrl = "http://localhost:8005/administracion/facturacion/nueva";
		
		String billDescription = "Viaje realizado el " + travel.getEndTime() + " en el monopatin " + travel.getScooterId() + " por el usuario " + travel.getUserId();
		NewBillDTO bill = new NewBillDTO(travel.getEndTime(), travel.getFare(), billDescription);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewBillDTO> requestEntity = new HttpEntity<>(bill, headers);

        ResponseEntity<Void> response = restTemplate.exchange(accountUrl, HttpMethod.POST, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al crear la factura");
        }
	}

	@Transactional
	public void updateUserAccount(long userId, double fare) throws Exception {
		List<AccountDTO> accounts = getUserAccounts(userId);
		AccountDTO account = accounts.stream().filter(a -> a.getSaldo() > 0).findFirst().orElse(null);
		if (Objects.isNull(account)) {
			 account = accounts.get(0);
		}
		account.setSaldo(account.getSaldo() - fare);
		try {
			updateAccountBalance(account);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error al actualizar la cuenta del usuario: " + userId);
		}
	}

	@Transactional(readOnly = true)
	public List<AccountDTO> getUserAccounts(long userId) throws Exception {
		String url = "http://localhost:8004/cuentas/usuario/" + userId;

		try {
			ResponseEntity<List<AccountDTO>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<AccountDTO>>() {}
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				// Handle non-OK status codes here
				throw new RuntimeException("Error al obtener las cuentas del usuario con ID " + userId + ". El servidor respondió con el código de estado " + response.getStatusCode());
			}
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific exceptions here
			String errorMessage = "Error al obtener las cuentas del usuario con ID " + userId + ". Se produjo una excepción de tipo " + 
			e.getClass().getSimpleName() + ": " + e.getMessage() + "\n" + "Causa: " + e.getResponseBodyAsString();
			throw new RuntimeException(errorMessage, e);
		} catch (Exception e) {
			// Handle all other exceptions here
			String errorMessage = "Error al obtener las cuentas del usuario con ID " + userId + ". Se produjo una excepción de tipo " + 
			e.getClass().getSimpleName() + ": " + e.getMessage() + "\n" + "Causa: " + e.getCause();
			throw new RuntimeException(errorMessage, e);
		}
    }

	@Transactional
	public void updateAccountBalance(AccountDTO account) throws Exception {
        String accountUrl = "http://localhost:8004/usuarios/actualizar/" + account.getAccountId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AccountDTO> requestEntity = new HttpEntity<>(account, headers);

        ResponseEntity<Void> response = restTemplate.exchange(accountUrl, HttpMethod.PUT, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al actualizar la cuenta del usuario");
        }
    }


	@Transactional
	public void delete(Long id) {
		travelRepository.delete(travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id)));
	}

	@Transactional
	public void update(Long id, TravelDTO entity) {
		Travel travel = travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id));
		travel.setUserId(entity.getUserId());
		travel.setScooterId(entity.getScooterId());
		travel.setFare(entity.getFare());
		travel.setEndTime(entity.getEndTime());		
		travelRepository.save(travel);
	}
	
	@Transactional
	public FareDTO saveFare(FareDTO fare) {
		return new FareDTO(this.fareRepository.save(new Fare(fare)));
	}
		
}
