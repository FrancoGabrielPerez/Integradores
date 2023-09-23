package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dtos.EstudianteCarreraDTO;
import dtos.EstudianteDTO;
import dtos.InformeCarreraCantEstudiantesDTO;
import dtos.InformeCarreraDTO;
import entity.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import repository.EstudianteCarreraRepositoryImpl;

public class EstudianteCarreraService extends EstudianteCarreraRepositoryImpl{
	
	private EntityManager em;

	public EstudianteCarreraService(EntityManager em) {
		super(em);
		this.em = em;
	}     
	
	public List<EstudianteCarreraDTO> getCarrerasOf(Estudiante es) {
		em.getTransaction().begin();
		String jpql = "SELECT NEW EstudianteCarreraDTO(CONCAT(e.nombre, ', ', e.apellido), c.nombre, ec.fechaInscripcion, ec.fechaGraduacion) " +
		"FROM EstudianteCarrera ec JOIN Estudiante e ON ec.estudiante.libreta = e.libreta " +
		"JOIN Carrera c ON ec.carrera.id = c.id " +
		"WHERE ec.estudiante.libreta = :estudiante";
		TypedQuery<EstudianteCarreraDTO> query = em.createQuery(jpql, EstudianteCarreraDTO.class);
		query.setParameter("estudiante", es.getLibreta());
		List<EstudianteCarreraDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}

	public List<InformeCarreraCantEstudiantesDTO> getCarrerasPorCantEstudiantes() {
		em.getTransaction().begin();
		String jpql = "SELECT NEW dtos.InformeCarreraCantEstudiantesDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
						"FROM EstudianteCarrera ec " +
						"JOIN ec.carrera c " +
						"GROUP BY ec.carrera " +
						"ORDER BY cantEstudiantes DESC";
		TypedQuery<InformeCarreraCantEstudiantesDTO> query = em.createQuery(jpql, InformeCarreraCantEstudiantesDTO.class);
		List<InformeCarreraCantEstudiantesDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	} 

	public List<EstudianteDTO> getListEstudiantePorCiudadResidendcia(String ciudad, String carrera) {
	    em.getTransaction().begin();
	    String jpqlf = "SELECT NEW dtos.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " +
	                     "FROM Estudiante e " +
	                    "WHERE e.ciudadResidencia = :ciudad " +
	                    "AND e.id IN (SELECT ec.estudiante.id FROM Carrera c JOIN c.estudiantes ec WHERE c.nombre = :carrera)";
	    TypedQuery<EstudianteDTO> query = em.createQuery(jpqlf, EstudianteDTO.class);
	    query.setParameter("ciudad", ciudad);
	    query.setParameter("carrera", carrera);
	    List<EstudianteDTO> informe = query.getResultList();
	    em.getTransaction().commit();
		return informe;
	}

	@SuppressWarnings("unchecked")
	public List<InformeCarreraDTO> getInformePorCarrera() {
		em.getTransaction().begin();
		String SQLquery = "SELECT nombre AS Carrera, Año, SUM(Inscriptos) AS Inscriptos, SUM(Graduados) AS Graduados " +
			"FROM " +
			"((SELECT id, nombre, YEAR(fecha_insc) AS Año, COUNT(*) AS Inscriptos, 0 AS Graduados " +
			"FROM carrera JOIN estudiante_carrera on carrera.id = estudiante_carrera.carrera_id " +
			"GROUP BY carrera.id, YEAR(estudiante_carrera.fecha_insc)) " +
			"UNION " +
			"(SELECT id, nombre, YEAR(fecha_grad) AS Año,0 AS Inscriptos, COUNT(*) AS Graduados " +
			"FROM carrera JOIN estudiante_carrera on id = carrera_id WHERE !ISNULL(fecha_grad) " +
			"GROUP BY id, YEAR(fecha_grad))) u " +
			"GROUP BY nombre, Año " +
			"ORDER BY nombre, Año";
		
		Query query = em.createNativeQuery(SQLquery);
		List<Object[]> res = query.getResultList();
		List<InformeCarreraDTO> informe = new ArrayList<>();
		for (Object[] row : res) {
			Long a = row[2] == null ? null : ((BigDecimal) row[2]).longValue();
			Long b = row[3] == null ? null : ((BigDecimal) row[3]).longValue();
			InformeCarreraDTO inf = new InformeCarreraDTO((String) row[0], (Integer) row[1], a, b);
			informe.add(inf);
		}
		em.getTransaction().commit();
		return informe;
	}
}
