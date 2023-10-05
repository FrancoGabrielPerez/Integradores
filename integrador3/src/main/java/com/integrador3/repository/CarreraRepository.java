package com.integrador3.repository;

import com.integrador3.model.Carrera;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository ("carreraRepository")
public interface CarreraRepository extends CarreraRepositoryCustom, JpaRepository<Carrera, Long> {
	Optional<Carrera> findByNombre(String nombre);
}

