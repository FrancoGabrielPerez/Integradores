package com.integrador3.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstudianteCarreraRepositoryCustomImpl implements EstudianteCarreraRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> listaCarrerasOrdenadasPorAnio() {
        return entityManager.createNativeQuery(
            "SELECT nombre AS Carrera, Año, SUM(Inscriptos) AS Inscriptos, SUM(Graduados) AS Graduados " +
			"FROM " +
			"((SELECT id, nombre, YEAR(fecha_insc) AS Año, COUNT(*) AS Inscriptos, 0 AS Graduados " +
			"FROM carrera JOIN estudiante_carrera on carrera.id = estudiante_carrera.carrera_id " +
			"GROUP BY carrera.id, YEAR(estudiante_carrera.fecha_insc)) " +
			"UNION " +
			"(SELECT id, nombre, YEAR(fecha_grad) AS Año,0 AS Inscriptos, COUNT(*) AS Graduados " +
			"FROM carrera JOIN estudiante_carrera on id = carrera_id WHERE !ISNULL(fecha_grad) " +
			"GROUP BY id, YEAR(fecha_grad))) u " +
			"GROUP BY nombre, Año " +
			"ORDER BY nombre, Año",
            Object[].class
        ).getResultList();
    }
}
