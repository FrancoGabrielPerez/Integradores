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
@Table(name = "fare")

public class Fare {
    @Column(name = "fareId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fareId;
    @Column(namte = "date")
    private Timestamp date;
    @Column(name = "flatRate")
    private double flatRate;
    @Column(name = "fullRate")
    private double fullRate;

    public Fare() {
        super();
    }

    public Fare(double flatRate, double fullRate, Timestamp date /* TODO set default to current date */) {
        this.flatRate = flatRate;
        this.fullRate = fullRate;
        this.date = date;
    }
}
