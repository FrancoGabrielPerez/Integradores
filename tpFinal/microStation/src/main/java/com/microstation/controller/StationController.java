package com.microstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microstation.dto.StationDTO;
import com.microstation.service.StationService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/estaciones")
public class StationController {
    
    @Autowired
    private StationService stationService;

    @Operation(summary = "Obtiene todas las estaciones.", description = "Obtiene todos las estaciones")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega una estacion.", description = "Agrega una estacion")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody StationDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(stationService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene una estacion por su id.", description = "Obtiene un estacion por su stationId")
    @GetMapping("/buscar/{stationId}")
    public ResponseEntity<?> getById(@PathVariable String stationId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findById(stationId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Eliminia una estacion por su id.", description = "Elimina una estacion por su stationId")
    @DeleteMapping("/eliminar/{stationId}")
    public ResponseEntity<?> delete(@PathVariable String stationId){
        try{
            stationService.delete(stationId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la estacion con stationId: " + stationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Actualiza los datos de una estacion por su id.", description = "Actualiza una estacion por su stationId")
    @PutMapping("/actualizar/{stationId}")
    public ResponseEntity<?> update(@PathVariable String stationId, @RequestBody StationDTO entity){
        try{
            stationService.update(stationId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos de la estacion con stationId: " + stationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
   
    @Operation(summary = "Verifica si las coordenadas son validas.", description = "Verifica que las coordenadas proveidas coinciden con als coordenadas de una estacion")
    @GetMapping("/verificar/latitud/{latitud}/longitud/{longitud}")
    public ResponseEntity<?> verify(@PathVariable String latitud, @PathVariable String longitud){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findByLatitudAndLongitud(latitud, longitud));
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo verificar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
