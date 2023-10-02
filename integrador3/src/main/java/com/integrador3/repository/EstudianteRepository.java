package com.integrador3.repository;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.model.Estudiante;

import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("estudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
   
	// @Query("SELECT NEW com.example.integrador3.dto.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) FROM Estudiante e 	WHERE (e.genero = :genero)")
   //  List<EstudianteDTO> genero(@Param("genero") String genero);

    // porque no se puede hacer con new DTO?
    // @Query("SELECT NEW dtos.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) FROM Estudiante e WHERE (e.genero = :genero)")
    // List<Estudiante> findAllByGeneroF(@Param("genero") String genero);

   //   @Query("SELECT new com.integrador3.dto.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " +
   //      "FROM Estudiante e " +
   //      "WHERE e.genero = :genero")
   //   List<EstudianteDTO> findAllByGenero(@Param("genero") String genero);
    List<Estudiante> findByGenero(String genero);
}

