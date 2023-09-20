package services;

import java.sql.Timestamp;
import java.util.Date;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteCarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;

public class CarreraService extends CarreraRepositoryImpl{
    private EstudianteCarreraRepositoryImpl inscriptos;
    private EntityManager em;

    public CarreraService(EntityManager em) {
        super(em);
        this.em = em;
        this.inscriptos = new EstudianteCarreraRepositoryImpl(em);
    }

    public Integer getCarreraIdByName(String name) {
        try {
            String jpqlCarrera = "SELECT c FROM Carrera c WHERE c.nombre = :nombre";
            TypedQuery<Carrera> query = em.createQuery(jpqlCarrera, Carrera.class);
            query.setParameter("nombre", name); 
            return (query.getSingleResult().getId());
        } catch (Exception e) {
            throw new NullPointerException("La carrera " + name + " no existe."); 
        }
    }
    
    public void matricular(Estudiante e, String c) {
        Integer carreraId = getCarreraIdByName(c);
        Carrera carrera = em.find(Carrera.class, carreraId);
        Date hoy = new Date();
        Timestamp ts = new Timestamp(hoy.getTime());
        EstudianteCarrera nuevo = new EstudianteCarrera(e, carrera, ts);            
        this.inscriptos.save(nuevo);
    }
}
