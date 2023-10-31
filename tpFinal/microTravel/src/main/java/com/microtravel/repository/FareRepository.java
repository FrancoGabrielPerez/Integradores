package com.microtravel.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microtravel.dto.FareDTO;
import com.microtravel.model.Fare;

@Repository ("fareRepository")
public interface FareRepository extends JpaRepository<Fare, Long> {

    Fare save(FareDTO fare);

    @Query("SELECT f.flatRate FROM Fare f WHERE f.date < CURRENT_DATE ORDER BY f.date DESC LIMIT 1")
    Double FlatRate();

    @Query("SELECT f.fullRate FROM Fare f WHERE f.date < CURRENT_DATE ORDER BY f.date DESC LIMIT 1")
    Double FullRate();
}

