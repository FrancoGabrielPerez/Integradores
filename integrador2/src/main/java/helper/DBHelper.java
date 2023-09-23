package helper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import factory.ServiceFactory;
import jakarta.persistence.EntityManager;
import service.CarreraService;
import service.EstudianteCarreraService;
import service.EstudianteService;
public class DBHelper {
	private EntityManager em;
	private CarreraService carreraRepo;
	private EstudianteService estudianteRepo;
	private EstudianteCarreraService estudianteCarreraRepo;
	private ServiceFactory repoFactory;

	public DBHelper(EntityManager em) {		
		this.em = em;
		this.repoFactory = ServiceFactory.getInstance(em);
		this.carreraRepo = repoFactory.getCarreraService();
		this.estudianteRepo = repoFactory.getEstudianteService();
		this.estudianteCarreraRepo = repoFactory.getEstudianteCarreraService();
	}    
	
	private Iterable<CSVRecord> getData(String archivo) throws IOException {
		String path = "integrador2\\src\\main\\resources\\" + archivo;
		Reader in = new FileReader(path);
		String[] header = {};
		CSVParser csvParser = CSVFormat.EXCEL.builder().setNullString("").setHeader(header).build().parse(in);		
		Iterable<CSVRecord> record = csvParser;
		return record;
	}

	public void populateDB() throws Exception {	
		System.out.println("Populating DB...");
		for(CSVRecord row : getData("carreras.csv")) {
			Carrera carrera = new Carrera((String) row.get(0));
			carreraRepo.save(carrera);
		}
		System.out.println("Carreras insertadas");
		for(CSVRecord row : getData("estudiantes.csv")) {
			Estudiante estudiante = new Estudiante((String) row.get(0), (String) row.get(1), 
							Integer.parseInt(row.get(2)), (String) row.get(3), (String) row.get(4),
							Integer.parseInt(row.get(5)), Integer.parseInt(row.get(6)));
			estudianteRepo.save(estudiante);
		}
		System.out.println("Estudiantes insertados");
		for(CSVRecord row : getData("estudiantes_carreras.csv")) {
			Timestamp ts = row.get(3) == null ? null : Timestamp.valueOf(row.get(3));
			EstudianteCarrera estudianteCarrera = new EstudianteCarrera((Estudiante) em.find(Estudiante.class, Integer.parseInt(row.get(0))), 
						(Carrera) em.find(Carrera.class, Integer.parseInt(row.get(1))), 
						(Timestamp) Timestamp.valueOf(row.get(2)), ts);
			estudianteCarreraRepo.save(estudianteCarrera);
		}
		System.out.println("Relacion Estudantes-Carrera insertadas");
	}        
}
