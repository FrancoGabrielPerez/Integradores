package services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import dtos.InformeCarreraCantEstudiantesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public List<InformeCarreraCantEstudiantesDTO> getCarrerasPorCantEstudiantes() {
        em.getTransaction().begin();
        String jpql = "SELECT NEW dtos.InformeCarreraCantEstudiantesDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
                        "FROM EstudianteCarrera ec " +
                        "JOIN ec.carrera c " +
                        "GROUP BY c.nombre " +
                        "ORDER BY cantEstudiantes DESC";
        TypedQuery<InformeCarreraCantEstudiantesDTO> query = em.createQuery(jpql, InformeCarreraCantEstudiantesDTO.class);
		List<InformeCarreraCantEstudiantesDTO> res = query.getResultList();
        em.getTransaction().commit();
        return res;
    } 
}
