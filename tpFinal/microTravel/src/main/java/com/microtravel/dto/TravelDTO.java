package com.microtravel.dto;

import java.sql.Timestamp;
import com.microtravel.model.Travel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * TravelDTO
 * 
 * DTO que contiene los atributos de un viaje.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Getter
@RequiredArgsConstructor
public class TravelDTO {
	
	private long travelId;
	private long userId;
	private long scooterId;
	private Timestamp endTime;
	private double useTime;
	private double pauseTime;
	private double kilometers;
	private Double fare;

	public TravelDTO(Travel travel) {
		this.travelId = travel.getTravelId();
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