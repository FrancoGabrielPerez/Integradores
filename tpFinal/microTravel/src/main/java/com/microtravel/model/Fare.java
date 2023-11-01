package com.microtravel.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

import com.microtravel.dto.FareDTO;

@Entity
@Data
@Table(name = "fare")

public class Fare {
    @Id
    @Column(name = "fareId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fareId;
    @Column(name = "date")
    private Timestamp date;
    @Column(name = "flatRate")
    private double flatRate;
    @Column(name = "fullRate")
    private double fullRate;

    public Fare() {
        super();
    }

    public Fare(double flatRate, double fullRate) {
        this(flatRate, fullRate,  new Timestamp(System.currentTimeMillis()));        
    }

    public Fare(double flatRate, double fullRate, Timestamp date) {
        this.flatRate = flatRate;
        this.fullRate = fullRate;
        this.date = date;
    }

    public Fare(FareDTO fareDTO) {
        this(fareDTO.getFlatRate(), fareDTO.getFullRate(), fareDTO.getDate());
    }
}
