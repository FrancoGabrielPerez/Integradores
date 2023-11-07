package com.microadministration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microadministration.model.Bill;

/**
 * BillRepository
 * 
 * Repositorio de facturacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Repository ("billRepository")
public interface BillRepository extends JpaRepository<Bill, Long> {

    /**
     * Busca la facturacion entre dos fechas.
     * @param fechaDesde
     * @param fechaHasta
     */
    @Query(value = "SELECT SUM(monto) FROM factura WHERE fecha_factura BETWEEN ?1 AND ?2", nativeQuery = true)
    Double getFacturacion(String fechaDesde, String fechaHasta);

}

