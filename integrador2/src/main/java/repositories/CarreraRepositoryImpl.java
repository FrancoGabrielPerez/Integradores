package repositories;

import java.util.List;

import entities.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CarreraRepositoryImpl implements EntityRepository<Carrera> {
    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

	@Override
	public Carrera save(Carrera entity) {
		em.getTransaction().begin();
		if (entity.getId() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public Carrera findById(int id) {
		em.getTransaction().begin();
		Carrera aux = em.find(Carrera.class, id);
		em.getTransaction().commit();
		return aux;

	}

	@Override
	public List<Carrera> findAll() {
		em.getTransaction().begin();
		List<Carrera> result;
        String jpql = "SELECT c FROM Carrera c";
        TypedQuery<Carrera> res = em.createQuery(jpql, Carrera.class);
		result = res.getResultList();
		em.getTransaction().commit();
        return result;
	}

	@Override
	public void delete(Carrera entity) {
		em.getTransaction().begin();
		if (em.contains(entity))
            em.remove(entity);
        else        
            em.merge(entity);
		em.getTransaction().commit();
	}

}