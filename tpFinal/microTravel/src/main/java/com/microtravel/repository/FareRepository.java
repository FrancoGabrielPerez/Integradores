package com.microtravel.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microtravel.model.Fare;

@Repository ("fareRepository")
public interface FareRepository extends JpaRepository<Fare, Long> {

    @Query("SELECT value.flatRate FROM Fare WHERE id = (SELECT MAX(id) FROM Fare)")
    Double getCurrentFlatRate();

    @Query("SELECT value.fullRate FROM Fare WHERE id = (SELECT MAX(id) FROM Fare)")
    Double getCurrentFullRate();
}

