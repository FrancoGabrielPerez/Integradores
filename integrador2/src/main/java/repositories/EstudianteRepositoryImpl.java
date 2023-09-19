package repositories;

import java.util.List;

import entities.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteRepositoryImpl implements EntityRepository<Estudiante> {
	EntityManager em;

	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Estudiante save(Estudiante entity) {
		if (entity.getId() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
		return entity;
	}

	@Override
	public Estudiante findById(int id) {
		em.getTransaction().begin();
		Estudiante aux = em.find(Estudiante.class, id);
		em.getTransaction().commit();
		return aux;

	}

	@Override
	public List<Estudiante> findAll() {
		em.getTransaction().begin();
		List<Estudiante> result;
        String jpql = "SELECT p FROM Equipo p";
        TypedQuery<Estudiante> res = em.createQuery(jpql, Estudiante.class);
		result = res.getResultList();
		em.getTransaction().commit();
        return result;
	}

	@Override
	public void delete(Estudiante entity) {
		em.getTransaction().begin();
		if (em.contains(entity))
            em.remove(entity);
        else        
            em.merge(entity);
		em.getTransaction().begin();
	}
	
}
