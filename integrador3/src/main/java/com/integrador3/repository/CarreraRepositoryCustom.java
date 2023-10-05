package com.integrador3.repository;

import java.util.List;

import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;

public interface CarreraRepositoryCustom {
    List<InformeCarreraCantEstudiantesDTO> carrerasOrdenadas();
}
