package com.integrador3.repository;

import com.integrador3.model.Estudiante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
   
    List<Estudiante> findByGenero(String genero);
    List<Estudiante> findAllByOrderByApellidoAscNombreAsc();

}

