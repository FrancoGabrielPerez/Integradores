package com.integrador3.repository;

import com.integrador3.model.Carrera;
import com.integrador3.model.Estudiante;
import com.integrador3.model.EstudianteCarrera;
import com.integrador3.model.EstudianteCarreraPK;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estudianteCarreraRepository")
public interface EstudianteCarreraRepository extends EstudianteCarreraRepositoryCustom, JpaRepository<EstudianteCarrera, EstudianteCarreraPK> {
	Optional<EstudianteCarrera> findByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
	void deleteByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
}

