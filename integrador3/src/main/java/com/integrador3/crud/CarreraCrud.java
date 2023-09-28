package com.integrador3.crud;

import com.integrador3.model.Carrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraCrud extends CrudRepository<Carrera, Long> {
}
