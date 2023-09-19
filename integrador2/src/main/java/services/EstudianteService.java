package services;

import java.util.ArrayList;
import java.util.List;

import dtos.EstudianteDTO;
import entities.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import repositories.EstudianteRepositoryImpl;

public class EstudianteService extends EstudianteRepositoryImpl{
    public EstudianteService(EntityManager em) {
        super(em);
    }
    
    //TODO

    public List<EstudianteDTO> getAllEstudiantesOrderByNombre(){
        this.em.getTransaction().begin();

		List<Estudiante> aux;
        List<EstudianteDTO> res = new ArrayList<EstudianteDTO>();
        String jpql = "SELECT p FROM Estudiante p ORDER BY p.nombre LIMIT 50";
        TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		aux = query.getResultList();
		this.em.getTransaction().commit();

        for (Estudiante es : aux) {
            res.add(new EstudianteDTO(es.getNombre(), es.getApellido(), es.getEdad(), es.getCiudad_residencia(), es.getGenero(),
            es.getDni(), es.getLibreta()));
        }
        return res;
    }
    
    public EstudianteDTO getEstudianteByLibreta(int libreta){
        this.em.getTransaction().begin();
        String jpql = "SELECT p FROM Estudiante p WHERE p.libreta = ?1";
        TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
        query.setParameter(1, libreta);
		Estudiante es = query.getSingleResult();
        this.em.getTransaction().commit();
        EstudianteDTO res = new EstudianteDTO(es.getNombre(), es.getApellido(), es.getEdad(), es.getCiudad_residencia(), es.getGenero(),
            es.getDni(), es.getLibreta());
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
