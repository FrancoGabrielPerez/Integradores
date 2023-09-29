package com.integrador3.crud;

import com.integrador3.model.Estudiante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCrud extends CrudRepository<Estudiante, Integer> {

    //public Estudiante save(Estudiante estudiante);

    //public Estudiante findEstudianteById(Integer libreta);

    // public void deleteById(long id);

    // public Iterable<Estudiante> findAll();

    //public Estudiante findByCodigo(String codigo);
    //public Estudiante findByNombre(String nombre);
    //public Estudiante findByApellido(String apellido);
    //public Estudiante findByCorreo(String correo);
    //public Estudiante findByTelefono(String telefono);
    //public Estudiante findByEdad(int edad);
    //public Estudiante findBySexo(String sexo);
    //public Estudiante findByCarrera(String carrera);
    //public Estudiante findByCodigo(String codigo);
    //public Estudiante findByCodigo(String codigo);
    //public Estudiante findByCodigo(String codigo);
    //public Estudiante findByCodigo(String codigo);
}
