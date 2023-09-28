package com.integrador3.repository;

import com.integrador3.model.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estudianteCarreraRepository")
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long> {
}

