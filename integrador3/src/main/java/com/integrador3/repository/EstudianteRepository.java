package com.integrador3.repository;

import com.integrador3.model.Estudiante;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT e FROM Estudiante e WHERE e.libreta = :libreta")
    Estudiante findEstudianteById(Integer libreta);
}

