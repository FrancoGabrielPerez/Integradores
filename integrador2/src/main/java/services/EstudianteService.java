package services;

import java.util.List;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.EstudianteRepositoryImpl;

public class EstudianteService extends EstudianteRepositoryImpl {
    public EstudianteService(EntityManager em) {
        super(em);
    }

    public List<EstudianteDTO> getAllEstudiantesOrderByNombre() {
        this.em.getTransaction().begin();
        String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre,p.apellido,p.edad,p.ciudad_residencia,p.genero,p.dni,p.libreta) " +
                        "FROM Estudiante p ORDER BY p.nombre LIMIT 50";
        TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		List<EstudianteDTO> res = query.getResultList();
		this.em.getTransaction().commit();        
        return res;
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
}
