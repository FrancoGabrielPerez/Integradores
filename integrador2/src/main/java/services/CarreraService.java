package services;

import entities.Carrera;
import entities.Estudiante;
import jakarta.persistence.EntityManager;
import repositories.CarreraRepositoryImpl;

public class CarreraService extends CarreraRepositoryImpl{

    public CarreraService(EntityManager em) {
        super(em);
        //TODO Auto-generated constructor stub
    }
    
    //TODO
}
