package com.integrador3.repository;

import java.util.List;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.dto.InformeCarreraDTO;

public interface EstudianteCarreraRepositoryCustom {
    List<InformeCarreraDTO> informeCarreras();
    List<EstudianteDTO> buscarPorCarrerasYCiudadResidencia(String carrera, String ciudad);
    List<String> getGeneros(); 
}
