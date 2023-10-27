package com.microstation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microstation.model.Station;

@Repository ("stationRepository")
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s WHERE longitud = ?2 AND latitud = ?1")
    Optional<Station> findByLatitudAndLongitud(String latitud, String longitud);
}

