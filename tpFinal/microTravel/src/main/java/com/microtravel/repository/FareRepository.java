package com.microtravel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microtravel.model.Fare;

/**
 * FareRepository
 * 
 * Repositorio de tarifas.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Repository ("fareRepository")
public interface FareRepository extends JpaRepository<Fare, Long> {

    /**
     * Devuelve la tarifa plana actual.
     * @return
     */
    @Query(value = "SELECT f.flat_rate FROM Fare f WHERE f.date < CURRENT_DATE ORDER BY f.date DESC LIMIT 1", nativeQuery = true)
    Double findFirstFlatRate();

    /**
     * Devuelve la tarifa completa actual.
     * @return
     */
    @Query(value = "SELECT f.full_rate FROM Fare f WHERE f.date < CURRENT_DATE ORDER BY f.date DESC LIMIT 1", nativeQuery = true)
    Double findFirstFullRate();
}
