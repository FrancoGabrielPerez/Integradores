package com.microtravel.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

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
	@Column(name = "use_time")
	private int useTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	@Column(name = "kilometers")
	private double kilometers;
	@Column(name = "pause_time")
	private int pauseTime;
	@Column(name = "fare")
	private Double fare;

	public Travel(){
		super();
	}	

	public Travel(long userId, long scooterId, int pauseTime, Double fare, int useTime, double scooterInitKms) {
		this.userId = userId;
		this.scooterId = scooterId;
		this.useTime = useTime;
		this.kilometers = scooterInitKms;	
		this.endTime = null;
		this.pauseTime = 0;
		this.fare = fare;
	}
}