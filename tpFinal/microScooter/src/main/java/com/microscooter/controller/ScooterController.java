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
    @GetMapping("/{scooterId}")
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
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el monopatin con scooterId: " + scooterId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el monopatin, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Actualiza los datos de un monopatin por su id.", description = "Actualiza un monopatin por su scooterId")
    @PutMapping("/actualizar/{scooterId}")
    public ResponseEntity<?> update(@PathVariable long scooterId, @RequestBody ScooterDTO entity){
        try{
            scooterService.update(scooterId,entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del monopatin con scooterId: " + entity.getScooterId());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtengo un reporte de monopatines ordenados por kilometros", description = "Obtengo un reporte de monopatines ordenados por kilometros")
    @GetMapping("/reporte/kilometros/sinTiempoDeUso")
    public ResponseEntity<?> getReporteByKilometros(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByKilometros());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtiene un reporte de monopatines por kilometros y tiempo de uso.", description = "Obtiene un reporte de monopatines por kilometros y tiempo de uso")
    @GetMapping("/reporte/kilometros/conTiempoDeUso")
    public ResponseEntity<?> getReporteByKilometrosConTiempoUso(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByKilometrosConTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo de uso", description = "Obtengo un reporte de monopatines ordenados por tiempo de uso")
    @GetMapping("/reporte/tiempoUso")
    public ResponseEntity<?> getReporteByTiempoUso(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }   

    @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)", description = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)")
    @GetMapping("/reporte/tiempoTotal")
    public ResponseEntity<?> getReporteByTiempoTotal(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByTiempoTotal());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    @Operation(summary = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento", description = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento")
    @GetMapping("/reporte/cantidadOperativosMantenimiento")
    public ResponseEntity<?> getReporteOperativosMantenimiento(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findOperativosMantenimiento());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    @Operation(summary = "Obtiene todos los monopatines.", 
                description = "Obtiene todos los monopatines cerca de una coordenada (ejemplo que funciona latitud -37.327754, longitud -59.138998)")
    @GetMapping("/{latitud}/{longitud}")
    public ResponseEntity<?> getAllCercanos(@PathVariable Double latitud, @PathVariable Double longitud){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScootersCercanos(latitud,longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

}