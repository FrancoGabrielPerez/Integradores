package com.integrador3.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.integrador3.dto.CarreraDTO;
import com.integrador3.dto.EstudianteDTO;
import com.integrador3.dto.InformeCarreraCantEstudiantesDTO;
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
	public void delete(CarreraDTO entity) {
		carreraRepository.delete(carreraRepository.findById(entity.getId()).orElseThrow(
			() -> new IllegalArgumentException("ID de Carrera invalido:" + entity.getId())));
	}

	@Transactional
	public void matricular(EstudianteDTO e, CarreraDTO c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e.getLibreta())
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e.getLibreta()));

		Carrera carrera = carreraRepository.findById(c.getId())
				.orElseThrow(() -> new IllegalArgumentException("ID de Carrera invalido:" + c.getId()));

		if (inscriptos.findByEstudianteAndCarrera(estudiante, carrera).isPresent()) {
			throw new IllegalArgumentException("El estudiante ya esta inscripto en esta carrera");
		}

		Timestamp currentTime = new Timestamp(new Date().getTime());
		EstudianteCarrera nuevo = new EstudianteCarrera(estudiante, carrera, currentTime, null);
		inscriptos.save(nuevo);
	}

	@Transactional
	public void desmatricular(EstudianteDTO e, CarreraDTO c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e.getLibreta())
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e.getLibreta()));

		Carrera carrera = carreraRepository.findById(c.getId())
				.orElseThrow(() -> new IllegalArgumentException("ID de Carrera invalido:" + c.getId()));

		inscriptos.deleteByEstudianteAndCarrera(estudiante, carrera);
	}

	@Transactional(readOnly = true)
	public List<InformeCarreraCantEstudiantesDTO> carrerasOrdenadas() {
		return this.carreraRepository.carrerasOrdenadas().stream().map(InformeCarreraCantEstudiantesDTO::new ).toList();
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
