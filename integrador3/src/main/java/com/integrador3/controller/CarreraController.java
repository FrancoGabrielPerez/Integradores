package com.integrador3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.integrador3.dto.CarreraDTO;
import com.integrador3.dto.EstudianteDTO;
import com.integrador3.service.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
    
    @Autowired
    private CarreraService carreraService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody CarreraDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/baja")
    public ResponseEntity<?> delete(@RequestBody CarreraDTO entity){
        try{
            carreraService.delete(entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la carrera: " + entity.getNombre());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la carrera, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestBody EstudianteDTO e, @RequestBody CarreraDTO c){
        try{
            carreraService.matricular(e, c);
            return ResponseEntity.status(HttpStatus.OK).body("Se matriculo correctamente el estudiante: " + e.getNombre() + " " + e.getApellido() + " en la carrera: " + c.getNombre());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo matricular el estudiante, revise los campos e intente nuevamente.\"\n\"error\":\""+ex.getMessage()+"\"}");
        }
    }

    @PostMapping("/desmatricular")
    public ResponseEntity<?> desmatricular(@RequestBody EstudianteDTO e, @RequestBody CarreraDTO c){
        try{
            carreraService.desmatricular(e, c);
            return ResponseEntity.status(HttpStatus.OK).body("Se desmatriculo correctamente el estudiante: " + e.getNombre() + " " + e.getApellido() + " en la carrera: " + c.getNombre());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desmatricular el estudiante, revise los campos e intente nuevamente.\"\n\"error\":\""+ex.getMessage()+"\"}");
        }
    }

    @GetMapping("/CarrearsPorCantEstudiantes")
    public ResponseEntity<?> carrerasOrdenadas() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.carrerasOrdenadas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
