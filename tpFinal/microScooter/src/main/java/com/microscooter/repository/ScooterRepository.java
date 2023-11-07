package com.microscooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microscooter.model.Scooter;

import java.util.List;
/**
 * ScooterRepository
 * 
 * Repositorio de la entidad Scooter.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Repository ("scooterRepository")
public interface ScooterRepository extends ScooterRepositoryCustom, JpaRepository<Scooter, Long> {
    
    /**
     * findAllByOrderByKilometrosDesc
     * Devuelve una lista de monopatines ordenados por kilometros.
     * @return
     */
    List<Scooter> findAllByOrderByKilometrosDesc();


    /**
     * findAllByOrderByTiempoDeUsoDesc
     * Devuelve una lista de monopatines ordenados por tiempo de uso.
     * @return
     */
    List<Scooter> findAllByOrderByTiempoDeUsoDesc();
}