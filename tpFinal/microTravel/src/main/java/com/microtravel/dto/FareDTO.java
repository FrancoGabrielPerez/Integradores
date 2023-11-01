package com.microtravel.dto;

import lombok.Data;

import java.sql.Timestamp;

import com.microtravel.model.Fare;


@Data
public class FareDTO {   
    private long fareId;
    private Timestamp date;
    private double flatRate;
    private double fullRate;

    public FareDTO() {
        super();
    }

    public FareDTO(double flatRate, double fullRate) {
        this(flatRate, fullRate,  new Timestamp(System.currentTimeMillis()));        
    }

    public FareDTO(double flatRate, double fullRate, Timestamp date) {
        this.flatRate = flatRate;
        this.fullRate = fullRate;
        this.date = date;
    }

    public FareDTO(Fare fare) {
        this.fareId = fare.getFareId();
        this.date = fare.getDate();
        this.flatRate = fare.getFlatRate();
        this.fullRate = fare.getFullRate();
    }
}
