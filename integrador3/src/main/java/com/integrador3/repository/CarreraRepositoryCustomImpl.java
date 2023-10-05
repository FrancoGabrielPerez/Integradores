package com.integrador3.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CarreraRepositoryCustomImpl implements CarreraRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<InformeCarreraCantEstudiantesDTO> carrerasOrdenadas(){
        return entityManager.createQuery(
            "SELECT new com.integrador3.dto.InformeCarreraCantEstudiantesDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
                        "FROM EstudianteCarrera ec " +
                        "JOIN ec.carrera c " +
                        "GROUP BY ec.carrera " +
                        "ORDER BY cantEstudiantes DESC",
            InformeCarreraCantEstudiantesDTO.class
        ).getResultList();
    }
}

   
