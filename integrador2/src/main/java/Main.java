
import java.sql.Timestamp;

import dtos.EstudianteDTO;
import dtos.InformeCarreraCantEstudiantesDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import helper.DBHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteCarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;
import services.CarreraService;
import services.EstudianteCarreraService;
import services.EstudianteService;

public class Main {
	public static void main (String[] args) {
		ConnectionFactory conn = new ConnectionFactory();
		EntityManager em = conn.createConnection();
		// em.getTransaction().begin();
		// String sql = "DROP DATABASE IF EXISTS integrador2; ";
		// Query nq = em.createNativeQuery(sql);
		// nq.executeUpdate();
		// em.getTransaction().commit();
		// conn.closeConnection(em);
		// conn = new ConnectionFactory();
		// em = conn.createConnection();
		// DBHelper helper = new DBHelper(em);
		// try {
		// 	helper.populateDB();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

		//em.getTransaction().begin();

		// EstudianteCarreraRepositoryImpl estCarrRepo = new EstudianteCarreraRepositoryImpl(em);
		// CarreraRepositoryImpl carrRepo = new CarreraRepositoryImpl(em);
		// EstudianteRepositoryImpl estRepo = new EstudianteRepositoryImpl(em);

		// Estudiante estudiante = new Estudiante();
		// estudiante.setNombre("Juan");
		// estudiante.setApellido("Perez");
		// estudiante.setEdad(21);
		// estudiante.setGenero("Masculino");
		// estudiante.setDni(12345678);
		// estudiante.setCiudad_residencia("Buenos Aires");
		// estudiante.setLibreta(987654);
		// estRepo.save(estudiante);

		// // Crear una carrera
		// Carrera carrera = new Carrera();
		// carrera.setNombre("Ingeniería Informática");
		// carrRepo.save(carrera);

		// // Crear una inscripción
		// EstudianteCarrera inscripcion = new EstudianteCarrera(null, carrera, Timestamp.valueOf("1965-10-08 12:00:00"), false);
		// estCarrRepo.save(inscripcion);
		// System.out.println(inscripcion.getAntiguedad());

		// Establecer las relaciones        
		// estudiante.setCarreras(inscripcion);
		// carrera.setEstudiantes(inscripcion);

		// EstudianteService es = new EstudianteService(em);
		// // for (String string : es.getValidSortingOptions()) {
		// //     System.out.println(string);
		// // }
		
		// for(EstudianteDTO est : es.getAllEstudiantesOrderBy("apellido; Slect etc")){
		//     System.out.println(est.getNombre());
		// }
		// System.out.println(es.getEstudianteByLibreta(25655));

		// EstudianteCarreraService ec = new EstudianteCarreraService(em);
		// for(InformeCarreraCantEstudiantesDTO info : ec.getCarrerasPorCantEstudiantes())
		//     System.out.println(info);

		// EstudianteService es = new EstudianteService(em);
		// Estudiante prueba = new Estudiante("Jose", "Perez", 33, "Tandil", "Male", 45000000, 20500);
		// es.save(prueba);
		// CarreraService cs = new CarreraService(em);  
		// cs.matricular(prueba, "Librarian");

		EstudianteCarreraService ecs = new EstudianteCarreraService(em);
		System.out.println(ecs.getListEstudiantePorCiudadResidendcia("Dallas", "Sales Analist").toString());

		conn.closeConnection(em);
	}
}
