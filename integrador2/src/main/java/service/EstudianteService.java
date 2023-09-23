package service;

import java.util.List;

import dtos.EstudianteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.EstudianteRepositoryImpl;

public class EstudianteService extends EstudianteRepositoryImpl {
	public EstudianteService(EntityManager em) {
		super(em);
	}

	public EstudianteDTO getEstudianteByLibreta(int libreta) {
		this.em.getTransaction().begin();
		String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre,p.apellido,p.edad,p.ciudadResidencia,p.genero,p.dni,p.libreta) " +
						"FROM Estudiante p WHERE p.libreta = ?1";
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter(1, libreta);
		EstudianteDTO res = query.getSingleResult();
		this.em.getTransaction().commit();
		return res;
	}

	public List<EstudianteDTO> getAllEstudiantesOrderByApellido() {
		this.em.getTransaction().begin();
		String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " +
				  "FROM Estudiante p ORDER BY p.apellido, p.nombre LIMIT 50";
	
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		List<EstudianteDTO> res = query.getResultList();
		this.em.getTransaction().commit();        
		return res;
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
						"WHERE (e.genero = :genero)" ;
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter("genero", genero);
		List<EstudianteDTO> res = query.setMaxResults(30).getResultList();
		em.getTransaction().commit();
		return res;
	}

}
