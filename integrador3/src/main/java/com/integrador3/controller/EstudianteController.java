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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("genero/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/ordenadosPorApellidoYNombre")
    public ResponseEntity<?> getAllSorted(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByOrderByApellidoAscNombreAsc());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/{libreta}")
    public ResponseEntity<?> getById(@PathVariable Integer libreta) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findById(libreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody EstudianteDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/{libreta}")
    public ResponseEntity<?> delete(@PathVariable Integer libreta){
        try{
            estudianteService.delete(libreta);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al estudiante con libreta: " + libreta);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el estudiante, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }
    @GetMapping("/buscarPor")
    public ResponseEntity<?> buscarPor(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.estudiantePorCiudadDeResidencia(carrera, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

}
