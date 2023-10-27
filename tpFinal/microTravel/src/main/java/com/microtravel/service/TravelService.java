package com.microtravel.service;

import java.net.URI;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.microtravel.dto.UserDTO;
import com.microtravel.dto.AccountDTO;
import com.microtravel.dto.NewBillDTO;
import com.microtravel.dto.TravelDTO;
import com.microtravel.dto.ScooterDTO;
import com.microtravel.dto.StationDTO;
import com.microtravel.model.Travel;
import com.microtravel.repository.FareRepository;
import com.microtravel.repository.TravelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient;
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
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
	}
	
	@Transactional
	public TravelDTO save(long idUsuario, long idScooter) throws IllegalArgumentException, RestClientException {
		ResponseEntity<UserDTO> user = restTemplate.getForEntity("http://localhost:8080/usuarios/buscar/" + idUsuario, UserDTO.class);
		if (user.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de usuario invalido: " + idUsuario);
		}
		ResponseEntity<List<AccountDTO>> accounts = restTemplate.exchange("http://localhost:8080/cuentas/usuario/" + idUsuario, 
											HttpMethod.GET, null, new ParameterizedTypeReference<List<AccountDTO>>() {});
		if (accounts.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("Error al obtener las cuentas del usuario: " + idUsuario);
		}
		boolean hasCredit = false;
		for(AccountDTO account : accounts.getBody()) {
			if (account.isHabilitada() && account.getBalance() > 0)
			hasCredit = true;
		}
		if (!hasCredit) {
			throw new IllegalArgumentException("El usuario no tiene saldo suficiente para realizar un viaje");
		}
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/" + idScooter, ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + idScooter);
		}
		if (!updateScooterState(idScooter, scooter.getBody(), "Ocupado")) {
			throw new IllegalArgumentException("El monopatin no se pudo actualizar");
		}
		TravelDTO res = new TravelDTO(this.travelRepository.save(new Travel(idUsuario, idScooter, 0, fareRepository.getCurrentFlatRate(), -scooter.getBody().getTiempoDeUso(), -scooter.getBody().getKilometros())));
		
		return res;
	}

	private boolean updateScooterState(long idScooter, ScooterDTO scooter, String estado) {
		String scooterUpdateUrl = "http://localhost:8002/monopatines/" + idScooter;
		scooter.setEstado(estado);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ScooterDTO> requestEntity = new HttpEntity<>(scooter, headers);

		ResponseEntity<ScooterDTO> scooterResponse = restTemplate.exchange(
			scooterUpdateUrl,
			HttpMethod.PUT,
			requestEntity,
			ScooterDTO.class
		);
		return scooterResponse.getStatusCode() == HttpStatus.OK;
	}

	@Transactional
	private Double getCurrentFlatFare() {
		return fareRepository.getCurrentFlatRate();
	}

	@Transactional
	private Double getCurrentFullRate() {
		return fareRepository.getCurrentFullRate();
	}

	@Transactional
	public void travelEnd(Long id) throws Exception {
		Travel travel = travelRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("No se encuentra el viaje con id: " + id));
		ResponseEntity<ScooterDTO> scooter = restTemplate.getForEntity("http://localhost:8002/monopatines/" + travel.getScooterId(), ScooterDTO.class);
		if (scooter.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + travel.getScooterId());
		}
		ResponseEntity<StationDTO> station = restTemplate.getForEntity("http://localhost:8001/estaciones/verificar/latitud/" + scooter.getBody().getLatitud() + "/longitud/" + scooter.getBody().getLongitud(), StationDTO.class);
		if (station.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("No se encuentra una estacion valida para el monopatin: " + travel.getScooterId());
		}
		travel.setPauseTime(scooter.getBody().getTiempoEnpausa() + scooter.getBody().getTiempoEnpausa());
		travel.setUseTime(scooter.getBody().getTiempoDeUso() + scooter.getBody().getTiempoDeUso());
		travel.setKilometers(scooter.getBody().getKilometros() + scooter.getBody().getKilometros());
		travel.setEndTime(new Timestamp(System.currentTimeMillis()));
		if (travel.getPauseTime() < TIEMPOLIMITE) {
			travel.setFare(travel.getUseTime() * getCurrentFlatFare());
		}
		else {
			travel.setFare(travel.getUseTime() * getCurrentFullRate());
		}	
			
		travelRepository.save(travel);
		updateScooterState(travel.getScooterId(), scooter.getBody(), "Disponible");
		updateUserAccount(travel.getUserId(), travel.getFare());
		sendBill(travel);
	}

	@Transactional
	public void sendBill(Travel travel) throws Exception {
		String accountUrl = "http://localhost:8006/adminiastracion/facturacion/nueva";
		
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
		AccountDTO account = accounts.stream().filter(a -> a.getBalance() > 0).findFirst().orElse(null);
		if (Objects.isNull(account)) { //TODO: si no tiene saldo, se usa la primer cuenta que encuentre
			 account = accounts.get(0);
		}
		account.setBalance(account.getBalance() - fare);
		try {
			updateAccountBalance(account);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error al actualizar la cuenta del usuario: " + userId);
		}
	}

	@Transactional(readOnly = true)
	public List<AccountDTO> getUserAccounts(long userId) throws Exception {
        String url = "localhost:8080/usuarios/cuentas/" + userId;
        ResponseEntity<AccountDTO[]> response = restTemplate.getForEntity(url, AccountDTO[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
			throw new Exception("Error al obtener las cuentas del usuario");
        }
    }

	@Transactional
	public void updateAccountBalance(AccountDTO account) throws Exception {
        String accountUrl = "http://localhost:8080/usuarios/actualizar/" + account.getAccountId();

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
			() -> new IllegalArgumentException("ID de estacion invalido: " + id));
		travel.setUserId(entity.getUserId());
		travel.setScooterId(entity.getScooterId());
		travel.setFare(entity.getFare());
		travel.setEndTime(entity.getEndTime());		
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
