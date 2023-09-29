package com.integrador3.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.integrador3.crud.EstudianteCrud;
import com.integrador3.model.Estudiante;
import com.integrador3.repository.EstudianteRepository;

@Service("PersonService")
public class EstudianteService {
    @Autowired
    private EstudianteRepository personRepository;

    @Autowired
    private EstudianteRepository addressRepository;
}
