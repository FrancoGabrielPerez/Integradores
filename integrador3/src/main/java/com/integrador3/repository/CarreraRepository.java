package com.integrador3.repository;

import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;
import com.integrador3.model.Carrera;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository ("carreraRepository")
public interface CarreraRepository extends CarreraRepositoryCustom, JpaRepository<Carrera, Long> {
	// @Query("SELECT c FROM Carrera c JOIN c.estudianteCarreras ec GROUP BY c.id ORDER BY COUNT(ec) DESC")
    // List<Carrera> findAllOrderByInscriptos();

	// @Query("SELECT c.nombre, COUNT(ec) as cantEstudiantes FROM Carrera c JOIN c.estudianteCarreras ec GROUP BY c.id ORDER BY COUNT(ec) DESC")
    // List<Object[]> carrerasOrdenadas();

    // @Query("SELECT c.nombre, COUNT(ec) as cantEstudiantes " +
    //         "FROM Carrera c JOIN estudianteCarreras ec " +
    //         "GROUP BY c.id ORDER BY COUNT(ec) DESC")
    //List<Object[]> getListOrderer();
}

