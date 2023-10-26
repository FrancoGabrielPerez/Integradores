package com.microtravel.dto;

import java.sql.Timestamp;
import java.time.Duration;

import com.microtravel.model.Travel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TravelDTO {
	
	private long userId;
	private long scooterId;
	// private Timestamp startTime;
	private Timestamp endTime;
	private double useTime;
	private double pauseTime;
	private double kilometers;
	//private Double pause;
	private Double fare;

	public TravelDTO(Travel travel) {
		this.userId = travel.getUserId();
		this.scooterId = travel.getScooterId();
		this.useTime = travel.getUseTime();
		this.pauseTime = travel.getPauseTime();
		this.fare = travel.getFare();
		this.endTime = travel.getEndTime();
		this.kilometers = travel.getKilometers();
	}

	public TravelDTO(long userId, long scooterId, double useTime, double pauseTime, Double fare, Timestamp endTime, double kilometers) {
		this.userId = userId;
		this.scooterId = scooterId;
		this.useTime = useTime;
		this.pauseTime = pauseTime;
		this.fare = fare;
		this.endTime = endTime;
		this.kilometers = kilometers;
	}
}