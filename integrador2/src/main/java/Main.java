
import java.sql.Timestamp;

import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;
import helper.DBHelper;
import jakarta.persistence.EntityManager;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteCarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;

public class Main {
    public static void main (String[] args) {
        ConnectionFactory conn = new ConnectionFactory();
        EntityManager em = conn.createConnection();
        DBHelper helper = new DBHelper(em);
        try {
            helper.populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        em.getTransaction().begin();

        EstudianteCarreraRepositoryImpl estCarrRepo = new EstudianteCarreraRepositoryImpl(em);
        CarreraRepositoryImpl carrRepo = new CarreraRepositoryImpl(em);
        EstudianteRepositoryImpl estRepo = new EstudianteRepositoryImpl(em);

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Perez");
        estudiante.setEdad(21);
        estudiante.setGenero("Masculino");
        estudiante.setDni(12345678);
        estudiante.setCiudad_residencia("Buenos Aires");
        estudiante.setLibreta(987654);
        estRepo.save(estudiante);

        // Crear una carrera
        Carrera carrera = new Carrera();
        carrera.setNombre("Ingeniería Informática");
        carrRepo.save(carrera);

        // Crear una inscripción
        Estudiante_Carrera inscripcion = new Estudiante_Carrera(estudiante, carrera, Timestamp.valueOf("2023-10-08 12:00:00"), false);
        estCarrRepo.save(inscripcion);
        System.out.println(inscripcion.getAntiguedad());

        // Establecer las relaciones        
        estudiante.setCarreras(inscripcion);
        carrera.setEstudiantes(inscripcion);

        

        em.getTransaction().commit();

        conn.closeConnection(em);
    }
}
