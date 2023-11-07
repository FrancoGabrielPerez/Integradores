package com.microscooter.repository;

import java.util.List;

import com.microscooter.dto.InformeEstadoMonopatinesDTO;
import com.microscooter.model.Scooter;

/**
 * ScooterRepositoryCustom
 * 
 * Repositorio custom de la entidad Scooter.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
public interface ScooterRepositoryCustom{
    List<Scooter> getTiempoTotal();

    /**
     * getCantidadOperativosMantenimiento
     * Devuelve un informe de la cantidad de monopatines operativos y en mantenimiento.
     * @return
     */
    InformeEstadoMonopatinesDTO getCantidadOperativosMantenimiento();
}