package com.integrador3.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.model.Estudiante;
import com.integrador3.repository.EstudianteRepository;

@Service("estudianteService")
public class EstudianteService{
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional (readOnly = true)
    public EstudianteDTO findById(Integer libreta) {
        return estudianteRepository.findById(libreta).map(EstudianteDTO::new).orElseThrow(
            () -> new IllegalArgumentException("ID de usuario invalido:" + libreta));
    }
    
    @Transactional
    public EstudianteDTO save(EstudianteDTO entity) {
        return new EstudianteDTO(this.estudianteRepository.save(new Estudiante(entity)));
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAll() {
        return this.estudianteRepository.findAll().stream().map(EstudianteDTO::new ).toList();
    }

    @Transactional
    public void delete(EstudianteDTO entity) {
        estudianteRepository.delete(estudianteRepository.findById(entity.getLibreta()).orElseThrow(
            () -> new IllegalArgumentException("ID de usuario invalido:" + entity.getLibreta())));
    }   

    @Transactional(readOnly = true)
    public List<EstudianteDTO> findByGenero(String genero) {
       return estudianteRepository.findByGenero(genero).stream().map(EstudianteDTO::new ).toList();
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAllByOrderByApellidoAscNombreAsc() {
        return estudianteRepository.findAllByOrderByApellidoAscNombreAsc().stream().map(EstudianteDTO::new ).toList();
    }
}
