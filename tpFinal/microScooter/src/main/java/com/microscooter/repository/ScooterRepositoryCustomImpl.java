package com.microscooter.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.microscooter.dto.InformeEstadoMonopatinesDTO;
import com.microscooter.model.Scooter;

@Repository
public class ScooterRepositoryCustomImpl implements ScooterRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	public InformeEstadoMonopatinesDTO getCantidadOperativosMantenimiento(){
		int monopatinesOperativos = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From scooter WHERE estado Like 'libre' OR estado LIKE 'ocupado'"
		).getSingleResult()).intValue();
		int monopatinesMantenimiento = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From scooter WHERE estado Like 'mantenimiento'"
		).getSingleResult()).intValue();
		return new InformeEstadoMonopatinesDTO(monopatinesMantenimiento,monopatinesOperativos);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Scooter> getTiempoTotal() {
		return entityManager.createNativeQuery(
			"SELECT * FROM scooter ORDER BY (tiempo_de_uso + tiempo_en_pausa) DESC",Scooter.class).getResultList();
	}

}