
import java.security.Provider.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import dtos.EstudianteDTO;
import dtos.InformeCarreraDTO;
import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import factory.ConnectionFactory;
import factory.ServiceFactory;
import helper.DBHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import repository.CarreraRepositoryImpl;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepositoryImpl;
import service.CarreraService;
import service.EstudianteCarreraService;
import service.EstudianteService;

public class Main {
	public static void main (String[] args) {
		// EntityManagerFactory emfDropeo = Persistence.createEntityManagerFactory("integrador2");
		// EntityManager emDropeo = emfDropeo.createEntityManager();

		EntityManager emDrop = ConnectionFactory.createConnection();
		emDrop.getTransaction().begin();
		String sql = "DROP DATABASE IF EXISTS integrador2; ";
		Query nq = emDrop.createNativeQuery(sql);
		nq.executeUpdate();
		emDrop.getTransaction().commit();	
		ConnectionFactory.closeConnection();	

		EntityManager em = ConnectionFactory.createConnection();
		if (em == null)
			System.out.println("SI");
		ServiceFactory serviceFactory = ServiceFactory.getInstance(em);

		DBHelper helper = new DBHelper(em);
		try {
			helper.populateDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
        
		//Inciso 2)e
		e(serviceFactory);
        
		// EstudianteCarreraService ecs = new EstudianteCarreraService(em);
		// System.out.println("//////////////////////////////////////////////////");
		// System.out.println(ecs.getListEstudiantePorCiudadResidendcia("Dallas", "Sales Analist").toString());
        
		EstudianteCarreraService ec = serviceFactory.getEstudianteCarreraService();
        for(InformeCarreraDTO info : ec.getInformePorCarrera())
		System.out.println(info);
		
		ConnectionFactory.closeConnection();
	}
	
	private static void e(ServiceFactory serviceFactory) {
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2)e:");
		System.out.println("//////////////////////////////////////////////////");
		EstudianteCarreraService ecs = serviceFactory.getEstudianteCarreraService();
		Scanner scanner = new Scanner(System.in);
		for (String string : ecs.getGeneros()) {
			System.out.println(string);
		}
		System.out.println("Ingrese el genero a filtrar: ");
		String genero = scanner.nextLine();
		System.out.println("Estudiantes de genero " + genero + ":");
		for(EstudianteDTO estudiante : ecs.getEstudiantesPorGenero(genero)) {
			System.out.println(estudiante);
		}
		scanner.close();
		System.out.println();
	}
}
