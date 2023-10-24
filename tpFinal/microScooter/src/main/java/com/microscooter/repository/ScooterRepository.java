package com.microscooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microscooter.model.Scooter;

@Repository ("scooterRepository")
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
}