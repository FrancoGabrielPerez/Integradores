package com.microtravel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microtravel.dto.TravelDTO;

import java.security.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "travel")
public class Travel {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="travel_id")
	private long travelId;

	@Column(name = "userId")
	private long userId;
	@Column(name = "scooterId")
	private long scooterId;
	@Column(name = "startTime")
	private Timestamp startTime;
	@Column(name = "endTime")
	private Timestamp endTime;
	@Column(name = "kilometers")
	private double kilometers;
	@Column(name = "pause")
	private Duration pause;
	@Column(name = "fare")
	private Double fare;

	public Travel(){
		super();
	}	

	public Travel(long userId, long scooterId, Double fare) {
		this.userId = userId;
		this.scooterId = scooterId;
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.endTime = null;
		this.kilometers = 0;
		this.pause = Duration.ZERO;
		this.fare = fare;
	}



	// public Travel(TravelDTO dto){
	// 	this.latitud = dto.getLatitud();
	// 	this.longitud = dto.getLongitud();
	// }
}