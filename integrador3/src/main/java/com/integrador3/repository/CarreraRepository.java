package com.integrador3.repository;

import com.integrador3.model.Carrera;
import com.integrador3.model.Estudiante;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("carreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    /*
    @Query("SELECT e FROM Estudiante e WHERE e.libreta = :libreta");
    Estudiante getEstudiantesPorLibreta(Integer libreta); 
    */
}

