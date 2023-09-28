package com.integrador3.crud;

import com.integrador3.model.EstudianteCarrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraCrud extends CrudRepository<EstudianteCarrera, Long> {
}
