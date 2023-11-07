package com.microscooter.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.microscooter.dto.InformeEstadoMonopatinesDTO;
import com.microscooter.model.Scooter;

/**
 * ScooterRepositoryCustomImpl
 * 
 * Repositorio custom de la entidad Scooter.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 
 */
@Repository
public class ScooterRepositoryCustomImpl implements ScooterRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * getCantidadOperativosMantenimiento
	 * Devuelve un informe de la cantidad de monopatines operativos y en mantenimiento.
	 */
	public InformeEstadoMonopatinesDTO getCantidadOperativosMantenimiento(){
		int monopatinesOperativos = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From scooter WHERE estado Like 'disponible' OR estado LIKE 'ocupado'"
		).getSingleResult()).intValue();
		int monopatinesMantenimiento = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From scooter WHERE estado Like 'mantenimiento'"
		).getSingleResult()).intValue();
		return new InformeEstadoMonopatinesDTO(monopatinesMantenimiento,monopatinesOperativos);
	}

	/**
	 * getTiempoTotal
	 * Devuelve una lista de monopatines ordenados por tiempo total de uso.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Scooter> getTiempoTotal() {
		return entityManager.createNativeQuery(
			"SELECT * FROM scooter ORDER BY (tiempo_de_uso + tiempo_en_pausa) DESC",Scooter.class).getResultList();
	}

}