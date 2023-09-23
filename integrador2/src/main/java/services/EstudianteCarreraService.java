package services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import dtos.EstudianteDTO;
import dtos.InformeCarreraCantEstudiantesDTO;
import dtos.InformeCarreraDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import repositories.EstudianteCarreraRepositoryImpl;

public class EstudianteCarreraService extends EstudianteCarreraRepositoryImpl{
	
	private EntityManager em;

	public EstudianteCarreraService(EntityManager em) {
		super(em);
		this.em = em;
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

	public List<String> getGeneros() {
		em.getTransaction().begin();
		String jpql = "SELECT DISTINCT e.genero FROM Estudiante e";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		List<String> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}

	public List<EstudianteDTO> getEstudiantesPorGenero(String genero) {
		em.getTransaction().begin();
		String jpql = "SELECT NEW dtos.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " +
						"FROM Estudiante e " +
						"WHERE e.genero = :genero";
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter("genero", genero);
		List<EstudianteDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}

	public List<InformeCarreraDTO> getInformePorCarreraJPQL() {
		em.getTransaction().begin();
		String SQLquery = "SELECT c.nombre AS Carrera, FUNCTION('YEAR', ec.fechaInscripcion) AS Año, SUM(CASE WHEN ec.fechaGraduado IS NOT NULL THEN 0 ELSE 1 END) AS Inscriptos, SUM(CASE WHEN ec.fechaGraduado IS NOT NULL THEN 1 ELSE 0 END) AS Graduados " +
		"FROM Carrera c " +
		"JOIN EstudianteCarrera ec On c.id = ec.carrera.id " +
		"GROUP BY c.nombre, FUNCTION('YEAR', ec.fechaInscripcion) " +
		"ORDER BY c.nombre, Año";
		String CGPT2 = "SELECT NEW dtos.InformeCarreraDTO(c.nombre AS carrera, FUNCTION('YEAR', ec.fecha) AS año, SUM(ec.Inscriptos) AS inscriptos, SUM(ec.Graduados) AS graduados) " +
		"FROM Carrera c JOIN ( " +
			"(SELECT ec1.carrera.id AS id, ec1.fechaInscripcion AS fecha, COUNT(*) AS Inscriptos, 0 AS Graduados " +
			"FROM EstudianteCarrera ec1 " +
			"GROUP BY id, FUNCTION('YEAR', ec1.fecha)) " +
			"UNION ALL " +
			"(SELECT ec2.carrera.id AS id, ec2.fechaGraduado AS fecha, 0 AS Inscriptos, COUNT(*) AS Graduados " +
			"FROM EstudianteCarrera ec2 " +
			"WHERE ec2.fechaGraduado IS NOT NULL " +
			"GROUP BY id, FUNCTION('YEAR', fecha)) " +
		") ec ON c.id = ec.id " +
		"GROUP BY c.nombre, año " +
		"ORDER BY c.nombre, año";
		String jpql = "SELECT NEW dtos.InformeCarreraDTO (u.nombre AS Carrera, u.año, SUM(u.inscriptos) AS inscriptos, SUM(u.graduados) AS graduados) " +
		"FROM " +
			"((SELECT c.id, c.nombre, YEAR(ec.fechaInscripcion) AS año, COUNT(c.nombre) AS inscriptos, 0 AS graduados " +
			"FROM Carrera c JOIN EstudianteCarrera ec on c.id = ec.carrera.id " +
			"GROUP BY c.id, YEAR(ec.fechaInscripcion)) " +
			"UNION "+
			"(SELECT id, nombre, YEAR(fechaGraduado) AS año,0 AS inscriptos, COUNT(*) AS graduados " +
			"FROM Carrera d JOIN EstudianteCarrera ed on d.id = ed.carrera.id WHERE NOT ISNULL(ed.fechaGraduado) " +
			"GROUP BY d.id, YEAR(ed.fechaGraduado))) u " +
		"GROUP BY u.nombre, u.año " +
		"ORDER BY u.nombre, u.año";
		//esto no anda T.T
		TypedQuery<InformeCarreraDTO> query = em.createQuery(CGPT2, InformeCarreraDTO.class);
		List<InformeCarreraDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}

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
