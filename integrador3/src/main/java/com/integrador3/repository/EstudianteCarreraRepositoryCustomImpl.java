package com.integrador3.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.dto.InformeCarreraDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstudianteCarreraRepositoryCustomImpl implements EstudianteCarreraRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
    @SuppressWarnings("unchecked")
    public List<InformeCarreraDTO> InformeCarreras() {
        return entityManager.createNativeQuery(
            "SELECT nombre AS carrera, Año AS año, SUM(Inscriptos) AS inscriptos, SUM(Graduados) AS graduados " +
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
            InformeCarreraDTO.class
        ).getResultList();
    }

    public List<EstudianteDTO> buscarPorCarrerasYCiudadResidencia(String carrera, String ciudad){
		return entityManager.createQuery(
			"SELECT NEW com.integrador3.dto.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.id) " +
						"FROM Estudiante e " +
						"WHERE e.ciudadResidencia = :ciudad " +
						"AND e.id IN (SELECT ec.estudiante.id FROM Carrera c JOIN c.estudiantes ec WHERE c.nombre = :carrera)",
			EstudianteDTO.class
		).setParameter("ciudad", ciudad)
		 .setParameter("carrera", carrera)
		 .getResultList();
	}
}
