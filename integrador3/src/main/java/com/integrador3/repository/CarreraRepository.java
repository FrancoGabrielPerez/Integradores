package com.integrador3.repository;

import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;
import com.integrador3.model.Carrera;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("carreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    // @Query("SELECT NEW com.integrador3.dto.InformeCarreraCantEstudiantesDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
	// 					"FROM EstudianteCarrera ec " +
	// 					"JOIN ec.carrera c " +
	// 					"GROUP BY ec.carrera " +
	// 					"ORDER BY cantEstudiantes DESC")
    // InformeCarreraCantEstudiantesDTO listCarrerasPorCantEstudiantes();
}

