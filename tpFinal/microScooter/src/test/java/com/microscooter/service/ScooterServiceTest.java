package com.microscooter.service;

import com.microscooter.dto.ScooterDTO;
import com.microscooter.model.Scooter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScooterServiceTest {
	@Autowired
	private ScooterService service;

	@Test
	void findById() {
		Scooter scooter12 = new Scooter();
		scooter12.setScooterId(12);
		scooter12.setLatitud("-37.326162");
		scooter12.setLongitud("-59.139982");
		scooter12.setEstado("ocupado");
		scooter12.setKilometros(318.42);
		scooter12.setTiempoDeUso(911);
		scooter12.setTiempoEnpausa(10);
		ScooterDTO scooter12DTO = new ScooterDTO(scooter12);
		ScooterDTO responseScooterDTO = service.findById((long)12);
		assertAll(
				() -> assertNotNull(responseScooterDTO),
				() -> assertEquals(scooter12DTO.getScooterId(),responseScooterDTO.getScooterId()),
				() -> assertEquals(scooter12DTO.getLatitud(),responseScooterDTO.getLatitud()),
				() -> assertEquals(scooter12DTO.getLongitud(),responseScooterDTO.getLongitud()),
				() -> assertEquals(scooter12DTO.getEstado(),responseScooterDTO.getEstado()),
				() -> assertEquals(scooter12DTO.getKilometros(),responseScooterDTO.getKilometros()),
				() -> assertEquals(scooter12DTO.getTiempoDeUso(),responseScooterDTO.getTiempoDeUso()),
				() -> assertEquals(scooter12DTO.getTiempoEnpausa(),responseScooterDTO.getTiempoEnpausa())
		);
	}

	@Test
	void getScootersCercanos() {
		Scooter scooter12 = new Scooter();
		scooter12.setScooterId(12);
		scooter12.setLatitud("-37.326162");
		scooter12.setLongitud("-59.139982");
		scooter12.setEstado("ocupado");
		scooter12.setKilometros(318.42);
		scooter12.setTiempoDeUso(911);
		scooter12.setTiempoEnpausa(10);
		Scooter scooter13 = new Scooter();
		scooter13.setScooterId(13);
		scooter13.setLatitud("-37.326392");
		scooter13.setLongitud("-59.136656");
		scooter13.setEstado("ocupado");
		scooter13.setKilometros(706.11);
		scooter13.setTiempoDeUso(178);
		scooter13.setTiempoEnpausa(479);
		Scooter scooter17 = new Scooter();
		scooter17.setScooterId(17);
		scooter17.setLatitud("-37.327996");
		scooter17.setLongitud("-59.140619");
		scooter17.setEstado("disponible");
		scooter17.setKilometros(336.99);
		scooter17.setTiempoDeUso(882);
		scooter17.setTiempoEnpausa(448);
		Scooter scooter20 = new Scooter();
		scooter20.setScooterId(20);
		scooter20.setLatitud("-37.328605");
		scooter20.setLongitud("-59.136094");
		scooter20.setEstado("mantenimiento");
		scooter20.setKilometros(304.43);
		scooter20.setTiempoDeUso(22);
		scooter20.setTiempoEnpausa(131);
		List<Scooter> scooters = new ArrayList<>();
		scooters.add(scooter12);
		scooters.add(scooter13);
		scooters.add(scooter17);
		scooters.add(scooter20);
		List<Scooter> scootersCercanos = service.getScootersCercanos(-37.327754, -59.138998);
		assertEquals(scooters,scootersCercanos);
	}
}