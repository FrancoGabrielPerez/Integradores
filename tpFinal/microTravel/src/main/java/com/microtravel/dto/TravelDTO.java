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
	private Timestamp startTime;
	private Timestamp endTime;
	private double kilometers;
	private Duration pause;
	private Double fare;

	public TravelDTO(Travel travel) {
		this.userId = travel.getUserId();
		this.scooterId = travel.getScooterId();
		this.startTime = travel.getStartTime();
		this.endTime = travel.getEndTime();
		this.kilometers = travel.getKilometers();
		this.pause = travel.getPause();
		this.fare = travel.getFare();
	}
}