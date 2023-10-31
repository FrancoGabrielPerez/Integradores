package com.microtravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microtravel.model.Travel;

@Repository ("travelRepository")
public interface TravelRepository extends JpaRepository<Travel, Long> {
}

