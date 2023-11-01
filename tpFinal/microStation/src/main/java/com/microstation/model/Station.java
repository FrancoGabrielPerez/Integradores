package com.microstation.model;

import jakarta.persistence.*;
import lombok.Data;
import com.microstation.dto.StationDTO;

@Entity
@Data
@Table(name = "station")
public class Station {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="station_id")
	private long stationId;
	@Column(name = "latitud")
	private String latitud;
	@Column(name = "longitud")
	private String longitud;

	public Station(){
		super();
	}

	public Station(String latitud, String longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Station(StationDTO dto){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
	}
}