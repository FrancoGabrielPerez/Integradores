package helper;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.text.html.parser.Entity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Carrera;
import entities.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;


import java.nio.file.Path;
import java.nio.file.Paths;

public class DBHelper {
    private EntityManager em;
    private CarreraRepositoryImpl carreraRepo;
    private EstudianteRepositoryImpl estudianteRepo;

    public DBHelper(EntityManager em) {
        this.em = em;
        this.carreraRepo = new CarreraRepositoryImpl(em);
        this.estudianteRepo = new EstudianteRepositoryImpl(em);
    }    
    
    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
		System.out.println(currentWorkingDir.normalize().toString());
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.builder().setHeader(header).build().parse(in);
        
        Iterable<CSVRecord> record = csvParser;
        return record;
    }

    public void populateDB() throws Exception {
        em.getTransaction().begin();
        System.out.println("Populating DB...");
        for(CSVRecord row : getData("carreras.csv")) {
            Carrera carrera = new Carrera((String) row.get(0));
            carreraRepo.save(carrera);
        }
        System.out.println("Carreras insertadas");
        for(CSVRecord row : getData("estudiantes.csv")) {
            Estudiante estudiante = new Estudiante((String) row.get(0), (String) row.get(1), (String) row.get(2), (String) row.get(3),
                                                    Integer.parseInt(row.get(4)), Integer.parseInt(row.get(5)));
            estudianteRepo.save(estudiante);
        }
        System.out.println("Estudiantes insertados");
        em.getTransaction().commit();
    }       
    

    
   
}
