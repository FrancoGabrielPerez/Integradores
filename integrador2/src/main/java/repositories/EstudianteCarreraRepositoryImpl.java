package repositories;

import java.util.List;

import entities.Estudiante_Carrera;
import jakarta.persistence.EntityManager;

public class EstudianteCarreraRepositoryImpl implements EntityRepository<Estudiante_Carrera> {
	EntityManager em;

	public EstudianteCarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Estudiante_Carrera save(Estudiante_Carrera entity) {
		if (entity.getId_estudiante() == null && entity.getId_carrera() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
		return entity;
	}

	@Override
	public Estudiante_Carrera findById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findById'");
	}

	@Override
	public List<Estudiante_Carrera> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}

	@Override
	public void delete(Estudiante_Carrera entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}
	
}
