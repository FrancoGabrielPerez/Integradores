package com.integrador3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.integrador3.dto.EstudianteDTO;
import com.integrador3.service.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("genero/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }


    //TODO: Implementar el metodo de ordenamiento
    @GetMapping("/OrderedByName")
    public ResponseEntity<?> getAllSorted(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{libreta}")
    public ResponseEntity<?> getEstudiantesPorLibreta(@PathVariable Integer libreta) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findById(libreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody EstudianteDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Perro entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(perroServicio.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }
    */

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestBody EstudianteDTO entity){
        try{
            estudianteService.delete(entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al estudiante con libreta: "+entity.getLibreta());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }
}
