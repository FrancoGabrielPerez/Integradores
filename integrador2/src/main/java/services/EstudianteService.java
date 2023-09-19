package services;

import java.util.ArrayList;
import java.util.List;

import dtos.EstudianteDTO;
import entities.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.EstudianteRepositoryImpl;

public class EstudianteService extends EstudianteRepositoryImpl{
    public EstudianteService(EntityManager em) {
        super(em);
    }
    
    //TODO

    public List<EstudianteDTO> getAllEstudiantesOrderByNombre(){
        this.em.getTransaction().begin();

        String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre,p.apellido,p.edad,p.ciudad_residencia,p.genero,p.dni,p.libreta) FROM Estudiante p ORDER BY p.nombre LIMIT 50";
        TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		List<EstudianteDTO> res = query.getResultList();
		this.em.getTransaction().commit();
        
        return res;
    }
    
    public EstudianteDTO getEstudianteByLibreta(int libreta){
        this.em.getTransaction().begin();
        String jpql = "SELECT NEW dtos.EstudianteDTO(p.nombre,p.apellido,p.edad,p.ciudad_residencia,p.genero,p.dni,p.libreta) FROM Estudiante p WHERE p.libreta = ?1";
        TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
        query.setParameter(1, libreta);
		EstudianteDTO res = query.getSingleResult();
        this.em.getTransaction().commit();

        return res;
    } 
    

    // List<Object[]> res = new ArrayList<>();
    //     List<InformePersonaTurno> informe = new ArrayList<InformePersonaTurno>();
       
    //     String jpql = "SELECT t.fecha, p.nombre, p.edad FROM Persona p " +                        
    //                     "JOIN p.turnos t WHERE t.id = :id ";

    //     // Senntencia SQL
    //     /* SELECT p.nombre, p.anios FROM Persona p                         
    //     JOIN turno_persona t ON p.id = t.jugadores_id
    //     WHERE t.turnos_id = 5; */
       
    //     Query query = this.em.createQuery(jpql);
    //     query.setParameter("id", id);
        
    //     res  = query.getResultList();
    //     for (Object[] o : res) {
    //         InformePersonaTurno info = new InformePersonaTurno((Timestamp) o[0], (String) o[1], (int) o[2]);
    //         informe.add(info);
    //     }
    
    //     return informe;
}
