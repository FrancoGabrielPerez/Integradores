package com.integrador3.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.integrador3.dto.CarreraDTO;
import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;
import com.integrador3.dto.InformeCarreraDTO;
import com.integrador3.model.Carrera;
import com.integrador3.model.Estudiante;
import com.integrador3.model.EstudianteCarrera;
import com.integrador3.repository.CarreraRepository;
import com.integrador3.repository.EstudianteCarreraRepository;
import com.integrador3.repository.EstudianteRepository;

@Service("carreraService")
public class CarreraService{
	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Autowired
	private EstudianteCarreraRepository inscriptos;

	@Transactional (readOnly = true)
	public CarreraDTO findById(Long id) {
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
	public void delete(Long id) {
		carreraRepository.delete(carreraRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Carrera invalido:" + id)));
	}

	@Transactional
	public void matricular(Integer e, String c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e)
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e));

		Carrera carrera = carreraRepository.findByNombre(c)
				.orElseThrow(() -> new IllegalArgumentException("ID de Carrera invalido:" + c));

		if (inscriptos.findByEstudianteAndCarrera(estudiante, carrera).isPresent()) {
			throw new IllegalArgumentException("El estudiante ya esta inscripto en esta carrera");
		}

		Timestamp currentTime = new Timestamp(new Date().getTime());
		EstudianteCarrera nuevo = new EstudianteCarrera(estudiante, carrera, currentTime, null);
		inscriptos.save(nuevo);
	}

	@Transactional
	public void desmatricular(Integer e, String c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e)
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e));

		Carrera carrera = carreraRepository.findByNombre(c)
				.orElseThrow(() -> new IllegalArgumentException("Nombre de Carrera invalido:" + c));

		inscriptos.deleteByEstudianteAndCarrera(estudiante, carrera);
	}

	@Transactional(readOnly = true)
	public List<InformeCarreraDTO> InformeCarreras() {
		return this.inscriptos.InformeCarreras();
	}

	@Transactional(readOnly = true)
	public List<InformeCarreraCantEstudiantesDTO> carrerasOrdenadas() {
		return this.carreraRepository.carrerasOrdenadas();
	}
}
