package com.microscooter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microscooter.dto.ScooterDTO;
import com.microscooter.service.ScooterService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/monopatines")
public class ScooterController {
    
    @Autowired
    private ScooterService scooterService;

    @Operation(summary = "Obtiene todas los monopatines.", description = "Obtiene todos los monopatines")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega un monopatin.", description = "Agrega un monopatin")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody ScooterDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un monopatin por su id.", description = "Obtiene un monopatin por su scooterId")
    @GetMapping("/buscar/{scooterId}")
    public ResponseEntity<?> getById(@PathVariable long scooterId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findById(scooterId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Eliminia un monopatin por su id.", description = "Elimina una monopatin por su scooterId")
    @DeleteMapping("/eliminar/{scooterId}")
    public ResponseEntity<?> delete(@PathVariable long scooterId){
        try{
            scooterService.delete(scooterId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la estacion con scooterId: " + scooterId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Actualiza los datos de un monopatin por su id.", description = "Actualiza un monopatin por su scooterId")
    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@RequestBody ScooterDTO entity){
        try{
            scooterService.update(entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del monopatin con scooterId: " + entity.getScooterId());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
   
}