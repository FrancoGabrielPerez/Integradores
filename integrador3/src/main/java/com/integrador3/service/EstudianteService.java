package com.integrador3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.model.Estudiante;
import com.integrador3.repository.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service("estudianteService")
public class EstudianteService{
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public EstudianteDTO findById(Integer libreta) {
        return estudianteRepository.findById(libreta).map(EstudianteDTO::new).orElseThrow(
            () -> new IllegalArgumentException("Invalid user Id:" + libreta));
    }
    
    @Transactional
    public EstudianteDTO save(EstudianteDTO entity) {
        Estudiante aux = new Estudiante(entity);
        Estudiante res = this.estudianteRepository.save(aux);
        return new EstudianteDTO(res.getNombre(),res.getApellido(),res.getEdad(),res.getCiudadResidencia(),
                                    res.getGenero(),res.getDni(),res.getId());
    }

    @Transactional
    public List<EstudianteDTO> findAll() {
        return this.estudianteRepository.findAll().stream().map(EstudianteDTO::new ).toList();
    }

    @Transactional
    public void delete(EstudianteDTO entity) {
        estudianteRepository.delete(estudianteRepository.findById(entity.getLibreta()).orElseThrow(
            () -> new IllegalArgumentException("Invalid user Id:" + entity.getLibreta())));
    }   

    @Transactional 
    public List<EstudianteDTO> findAllByGenero(String genero) {
        List<EstudianteDTO> res = new ArrayList<EstudianteDTO>();
        for (Estudiante e : estudianteRepository.findAllByGenero(genero)) {
            System.out.println(e.getNombre());
            res.add(new EstudianteDTO(e));
        }
        return res;
    }
}
