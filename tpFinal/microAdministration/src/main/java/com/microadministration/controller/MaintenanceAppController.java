package com.microadministration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microadministration.service.MaintenanceService;


@RestController
@RequestMapping("/mantenimiento")
public class MaintenanceAppController {
    
    @Autowired
    private MaintenanceService maintenanceService;
    
    @PutMapping("/monopatines/actualizarEstado/{id}/estado/{estado}")
    public ResponseEntity<?> actualizarEstado(@PathVariable long id, @PathVariable String estado){
        try{
            maintenanceService.updateScooterState(id, estado);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el estado del scooter: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el estado del scooter, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    // @Operation(summary = "Agrega un nuevo intregrante al staff.", description = "Agrega un nuevo intregrante al staff")
    // @PostMapping("/alta")
    // public ResponseEntity<?> save(@RequestBody AdminStaffDTO entity){
    //     try{            
    //         return ResponseEntity.status(HttpStatus.OK).body(adminService.save(entity));
    //     }catch (Exception e){
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
    //     }
    // }
    
    // @Operation(summary = "Obtiene un integrante por su id.", description = "Obtiene un integrante del staff por su adminId")
    // @GetMapping("/buscar/{adminId}")
    // public ResponseEntity<?> getById(@PathVariable long adminId) {
    //     try{
    //         return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(adminId));
    //     }catch (Exception e){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
    //     }
    // }

    // @Operation(summary = "Elimina un integrante del staff por su id.", description = "Elimina un integrante del staff por su adminId")
    // @DeleteMapping("/eliminar/{adminId}")
    // public ResponseEntity<?> delete(@PathVariable long adminId){
    //     try{
    //         adminService.delete(adminId);
    //         return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al integrante con adminId: " + adminId);
    //     }catch (Exception e){
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el integrante, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
    //     }
    // }

    // @Operation(summary = "Actualiza los datos de un integrante del staff por su id.", description = "Actualiza un integrante del staff por su id")
    // @PutMapping("/actualizar/{adminId}")
    // public ResponseEntity<?> update(@PathVariable long adminId, @RequestBody AdminStaffDTO entity){
    //     try{
    //         adminService.update(adminId, entity);
    //         return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con adminId: " + adminId);
    //     }catch (Exception e){
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
    //     }
    // }

    
}
