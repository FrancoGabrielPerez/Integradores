package repositories;

import java.util.List;

import entities.Estudiante;
import jakarta.persistence.EntityManager;

public class EstudianteRepositoryImpl implements EntityRepository<Estudiante> {
	EntityManager em;

	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Estudiante save(Estudiante entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

	@Override
	public Estudiante findById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findById'");
	}

	@Override
	public List<Estudiante> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}

	@Override
	public void delete(Estudiante entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}
	
}
