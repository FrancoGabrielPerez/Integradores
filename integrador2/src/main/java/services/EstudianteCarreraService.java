package services;

import java.util.List;

import dtos.EstudianteDTO;
import dtos.InformeCarreraCantEstudiantesDTO;
import jakarta.persistence.EntityManager;
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
}
