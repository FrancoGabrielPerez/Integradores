package com.integrador3.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CarreraRepositoryCustomImpl implements CarreraRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> carrerasOrdenadas() {
        return entityManager.createQuery(
            "SELECT c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes " +
						"FROM EstudianteCarrera ec " +
						"JOIN ec.carrera c " +
						"GROUP BY ec.carrera " +
						"ORDER BY cantEstudiantes DESC",
            Object[].class
        ).getResultList();
    }
}
