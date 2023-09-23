package repository;

import java.util.List;

public interface EntityRepository <T> {
	// Create or update an entity
	public T save(T entity);

	// Find an entity by its primary key
	public T findById(int id);

	// Find all entities of this type
	public List<T> findAll();

	// Delete an entity
	public void delete(T entity);
}