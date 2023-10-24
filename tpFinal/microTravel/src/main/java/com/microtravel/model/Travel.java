package com.microtravel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microtravel.dto.TravelDTO;

import java.sql.Timestamp;
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

	@Column(name = "user_id")
	private long userId;
	@Column(name = "scooter_id")
	private long scooterId;
	@Column(name = "start_time")
	private Timestamp startTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	@Column(name = "scooter_start_kms")
	private double scooterStartKms;
	@Column(name = "scooter_end_kms")
	private double scooterEndKms;
	@Column(name = "pause_time")
	private Duration pause;
	@Column(name = "fare")
	private Double fare;

	public Travel(){
		super();
	}	

	public Travel(long userId, long scooterId, Double fare, double scooterStartKms) {
		this.userId = userId;
		this.scooterId = scooterId;
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.endTime = null;
		this.scooterStartKms = scooterStartKms;
		this.scooterEndKms = 0;
		this.pause = Duration.ZERO;
		this.fare = fare;
	}



	// public Travel(TravelDTO dto){
	// 	this.latitud = dto.getLatitud();
	// 	this.longitud = dto.getLongitud();
	// }
}