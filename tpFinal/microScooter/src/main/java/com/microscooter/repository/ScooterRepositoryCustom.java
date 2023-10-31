package com.microscooter.repository;

import java.util.List;

import com.microscooter.dto.InformeEstadoMonopatinesDTO;
import com.microscooter.model.Scooter;

public interface ScooterRepositoryCustom{
    List<Scooter> getTiempoTotal();

    InformeEstadoMonopatinesDTO getCantidadOperativosMantenimiento();
}