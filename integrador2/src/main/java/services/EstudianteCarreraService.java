package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import dtos.InformeCarreraCantEstudiantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import repositories.EstudianteCarreraRepositoryImpl;

public class EstudianteCarreraService extends EstudianteCarreraRepositoryImpl{
    private EstudianteCarreraRepositoryImpl inscripcion;
    private EntityManager em;

    public EstudianteCarreraService(EntityManager em) {
        super(em);
        this.em = em;
        this.inscripcion = new EstudianteCarreraRepositoryImpl(em);
    }
    

    public void matricular(Estudiante e, Carrera c) {
        Date hoy = new Date();
        EstudianteCarrera nuevo = new EstudianteCarrera(e, c, (Timestamp) hoy);
        this.inscripcion.save(nuevo);
    }

    public List<InformeCarreraCantEstudiantes> getCarrerasPorCantEstudiantes() {
        List<InformeCarreraCantEstudiantes> res = new ArrayList<>();
        String jpql = "SELECT c.nombre, COUNT(DISTINCT ec.estudiante) AS cantidad_estudiantes " +
                        "FROM EstudianteCarrera ec " +
                        "JOIN ec.carrera c " +
                        "GROUP BY c.nombre " +
                        "ORDER BY cantidad_estudiantes DESC";
        Query query = em.createQuery(jpql);
    } 
}
