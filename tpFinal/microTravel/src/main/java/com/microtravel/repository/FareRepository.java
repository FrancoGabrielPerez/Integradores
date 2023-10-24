package com.microtravel.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microtravel.model.Fare;

@Repository ("fareRepository")
public interface FareRepository extends JpaRepository<Fare, Long> {

    @Query("SELECT valor FROM Fare WHERE id = (SELECT MAX(id) FROM Fare)")
    Double getCurrentFare();
}

