package repositories;

import java.util.List;

import entities.Carrera;
import jakarta.persistence.EntityManager;

public class CarreraRepositoryImpl implements EntityRepository<Carrera> {
    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

	@Override
	public Carrera save(Carrera entity) {
		if (entity.getId() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
		return entity;
	}

	@Override
	public Carrera findById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findById'");
	}

	@Override
	public List<Carrera> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}

	@Override
	public void delete(Carrera entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

}