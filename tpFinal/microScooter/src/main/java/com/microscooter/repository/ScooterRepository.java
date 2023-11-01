package com.microscooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microscooter.model.Scooter;

import java.util.List;

@Repository ("scooterRepository")
public interface ScooterRepository extends ScooterRepositoryCustom, JpaRepository<Scooter, Long> {
    List<Scooter> findAllByOrderByKilometrosDesc();

    List<Scooter> findAllByOrderByTiempoDeUsoDesc();
}