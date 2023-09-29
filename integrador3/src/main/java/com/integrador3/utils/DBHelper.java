package com.integrador3.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import jakarta.persistence.EntityManager;

import com.integrador3.model.Carrera;
import com.integrador3.model.Estudiante;
import com.integrador3.model.EstudianteCarrera;
import com.integrador3.repository.CarreraRepository;
import com.integrador3.repository.EstudianteCarreraRepository;
import com.integrador3.repository.EstudianteRepository;
import com.integrador3.service.EstudianteService;

@Component
public class DBHelper {
	
    private final EstudianteRepository estudianteRepository;
	private final EstudianteCarreraRepository estudianteCarreraRepository;
	private final CarreraRepository carreraRepository;

    
    public DBHelper(EstudianteRepository estudianteRepository,EstudianteCarreraRepository estudianteCarreraRepository,
					 CarreraRepository carreraRepository) {
        this.estudianteCarreraRepository = estudianteCarreraRepository;
		this.carreraRepository = carreraRepository;
		this.estudianteRepository = estudianteRepository;
    }
	
	private Iterable<CSVRecord> getData(String archivo) throws IOException {
		String path = "integrador3\\src\\main\\resources\\" + archivo;
		Reader in = new FileReader(path);
		String[] header = {};
		CSVParser csvParser = CSVFormat.EXCEL.builder().setNullString("").setHeader(header).build().parse(in);		
		Iterable<CSVRecord> record = csvParser;
		return record;
	}
	
	@Autowired
	public void populateCarrerasDB() throws IOException {	
		for(CSVRecord row : getData("carreras.csv")) {
			Carrera carrera = new Carrera((String) row.get(0));
			carreraRepository.save(carrera);
		}		
	}
	
	@Autowired
	public void populateEstudiantesDB() throws IOException {
		for(CSVRecord row : getData("estudiantes.csv")) {
			Estudiante estudiante = new Estudiante((String) row.get(0), (String) row.get(1), 
							Integer.parseInt(row.get(2)), (String) row.get(3), (String) row.get(4),
							Integer.parseInt(row.get(5)), Integer.parseInt(row.get(6)));
			estudianteRepository.save(estudiante);
		}
	}
	
	@Autowired
	public void populateEstudiantesCarrerasDB() throws IOException {
		for(CSVRecord row : getData("estudiantes_carreras_acotado.csv")) {
			Timestamp ts = row.get(3) == null ? null : Timestamp.valueOf(row.get(3));
			EstudianteCarrera estudianteCarrera = new EstudianteCarrera((Estudiante) estudianteRepository.findEstudianteById(Integer.valueOf(row.get(0))),		
						(Carrera) carreraRepository.findCarreraById(Integer.valueOf(row.get(1))), 
						(Timestamp) Timestamp.valueOf(row.get(2)), ts);
			estudianteCarreraRepository.save(estudianteCarrera);
		}
	}        
}
