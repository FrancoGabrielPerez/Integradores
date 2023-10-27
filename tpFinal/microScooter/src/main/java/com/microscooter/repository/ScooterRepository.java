package com.microscooter.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microscooter.dto.ScooterDTO;
import com.microscooter.dto.ScooterReporteKmsTiempoUsoDTO;
import com.microscooter.model.Scooter;

import java.util.Collection;
import java.util.List;

@Repository ("scooterRepository")
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    List<Scooter> findAllByOrderByKilometrosDesc();

    List<Scooter> findAllByOrderByTiempoDeUsoDesc();

    // @Query("SELECT e.scooterId, e.kilometros, e.tiempoDeUso FROM Scooter e ORDER BY e.kilometros DESC")
    // List<Object> searchScootersByKilometrosAndTiempoDeUso();

    @Query("SELECT new com.microscooter.dto.ScooterReporteKmsTiempoUsoDTO(s.scooterId, s.kilometros, s.tiempoDeUso) FROM Scooter s ORDER BY s.kilometros DESC")
    List<ScooterReporteKmsTiempoUsoDTO> buscarKilometrosAndTiempoDeUso();
    /*
    @Query("SELECT e FROM Scooter e ORDER BY SUM(e.tiempoDeUso + e.tiempoEnPausa)")
    List<Scooter> tiempoUsoYPausa();
    */
}