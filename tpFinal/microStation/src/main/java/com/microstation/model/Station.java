package com.microstation.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microstation.dto.StationDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	//private List<Scooter> scooters; TODO: ver como hacer la relacion con scooter

	public Station(){
		super();
		// this.scooters = new ArrayList<>();
	}

	public Station(String latitud, String longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		// this.scooters = new ArrayList<>();
	}

	public Station(StationDTO dto){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		// this.scooters = new ArrayList<>();
	}
}