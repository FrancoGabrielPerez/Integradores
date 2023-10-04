package com.integrador3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.integrador3.dto.CarreraDTO;
import com.integrador3.model.Carrera;
import com.integrador3.repository.CarreraRepository;

@Service("carreraService")
public class CarreraService{
    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional (readOnly = true)
    public CarreraDTO findById(Integer id) {
        return carreraRepository.findById(id).map(CarreraDTO::new).orElseThrow(
            () -> new IllegalArgumentException("ID de carrera invalido:" + id));
    }
    
    @Transactional
    public CarreraDTO save(CarreraDTO entity) {
        return new CarreraDTO(this.carreraRepository.save(new Carrera(entity)));
    }

    @Transactional(readOnly = true)
    public List<CarreraDTO> findAll() {
        return this.carreraRepository.findAll().stream().map(CarreraDTO::new ).toList();
    }

    @Transactional
    public void delete(CarreraDTO entity) {
        carreraRepository.delete(carreraRepository.findById(entity.getId()).orElseThrow(
            () -> new IllegalArgumentException("ID de Carrera invalido:" + entity.getId())));
    }   

    // @Transactional(readOnly = true)
    // public List<CarreraDTO> findByGenero(String genero) {
    //    return CarreraRepository.findByGenero(genero).stream().map(CarreraDTO::new ).toList();
    // }

    // @Transactional(readOnly = true)
    // public List<CarreraDTO> findAllByOrderByApellidoAscNombreAsc() {
    //     return CarreraRepository.findAllByOrderByApellidoAscNombreAsc().stream().map(CarreraDTO::new ).toList();
    // }

    //InformeCarreraCantEstudiantesDTO listCarrerasPorCantEstudiantes();
}
