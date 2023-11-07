package com.microadministration.dto;

import lombok.Data;
import java.sql.Timestamp;

/**
 * FareDTO
 * 
 * DTO que contiene los atributos de una tarifa.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
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
}
