package com.microtravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microtravel.dto.TravelDTO;
import com.microtravel.service.TravelService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/estaciones")
public class TravelController {
    
    @Autowired
    private TravelService travelService;

    @Operation(summary = "Obtiene todos los viajes.", description = "Obtiene todos los viajes")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega un viaje.", description = "Agrega un viaje")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody TravelDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(travelService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene una estacion viaje por su id.", description = "Obtiene un viaje por su travelId")
    @GetMapping("/buscar/{travelId}")
    public ResponseEntity<?> getById(@PathVariable long travelId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.findById(travelId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Eliminia un viaje por su id.", description = "Elimina un viaje por su travelId")
    @DeleteMapping("/eliminar/{travelId}")
    public ResponseEntity<?> delete(@PathVariable long travelId){
        try{
            travelService.delete(travelId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con travelId: " + travelId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Actualiza los datos de un viaje por su id.", description = "Actualiza los datos de un viaje por su travelId")
    @PutMapping("/actualizar/{travelId}")
    public ResponseEntity<?> update(@PathVariable long travelId, @RequestBody TravelDTO entity){
        try{
            travelService.update(travelId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del viaje con travelId: " + travelId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
   
}
