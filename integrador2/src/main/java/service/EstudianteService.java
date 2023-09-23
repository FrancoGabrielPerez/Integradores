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
		String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre,p.apellido,p.edad,p.ciudad_residencia,p.genero,p.dni,p.libreta) " +
						"FROM Estudiante p WHERE p.libreta = ?1";
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter(1, libreta);
		EstudianteDTO res = query.getSingleResult();
		this.em.getTransaction().commit();
		return res;
	}

	public List<EstudianteDTO> getAllEstudiantesOrderByNombre() {
		this.em.getTransaction().begin();
		String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudad_residencia, p.genero, p.dni, p.libreta) " +
				  "FROM Estudiante p ORDER BY p.apellido, p.nombre LIMIT 50";
	
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		List<EstudianteDTO> res = query.getResultList();
		this.em.getTransaction().commit();        
		return res;
	}
}