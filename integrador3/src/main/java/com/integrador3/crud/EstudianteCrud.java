package com.integrador3.crud;

import com.integrador3.model.Estudiante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCrud extends CrudRepository<Estudiante, Long> {
}
