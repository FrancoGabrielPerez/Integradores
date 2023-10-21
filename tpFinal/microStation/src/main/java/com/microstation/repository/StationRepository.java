package com.microstation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstation.model.Station;

@Repository ("stationRepository")
public interface StationRepository extends JpaRepository<Station, Long> {
}

