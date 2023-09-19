package repositories;

import java.util.List;

import entities.EstudianteCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteCarreraRepositoryImpl implements EntityRepository<EstudianteCarrera> {
	EntityManager em;

	public EstudianteCarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public EstudianteCarrera save(EstudianteCarrera entity) {
		em.getTransaction().begin();
		if (entity.getId_estudiante() == null && entity.getId_carrera() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public EstudianteCarrera findById(int id) {
		em.getTransaction().begin();
		EstudianteCarrera aux = em.find(EstudianteCarrera.class, id);
		em.getTransaction().commit();
		return aux;

	}

	@Override
	public List<EstudianteCarrera> findAll() {
		em.getTransaction().begin();
		List<EstudianteCarrera> result;
        String jpql = "SELECT p FROM Equipo p";
        TypedQuery<EstudianteCarrera> res = em.createQuery(jpql, EstudianteCarrera.class);
		result = res.getResultList();
		em.getTransaction().commit();
        return result;
	}

	@Override
	public void delete(EstudianteCarrera entity) {
		em.getTransaction().begin();
		if (em.contains(entity))
            em.remove(entity);
        else        
            em.merge(entity);
		em.getTransaction().begin();
	}	
}
