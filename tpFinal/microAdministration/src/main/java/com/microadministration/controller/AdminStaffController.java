package com.microadministration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.service.AdminStaffService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * AdminStaffController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Staff.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("/administracion/staff")
public class AdminStaffController {
    
    @Autowired
    private AdminStaffService adminService;

    /**
     * getAll
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todos los integrantes del staff de administracion.", description = "Obtiene todos los integrantes del staff de administracion.")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getByRol
     * Obtiene todos los integrantes del staff de administracion por rol.
     * @param rol
     * @return ResponseEntity<?>
     */
    @GetMapping("/buscar/rol/{rol}")
    public ResponseEntity<?> getByRol(@PathVariable String rol){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findByRol(rol));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Agrega un nuevo intregrante al staff.", description = "Agrega un nuevo intregrante al staff")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody AdminStaffDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(adminService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * getById
     * Obtiene un integrante del staff por su id.
     * @param staffId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un integrante por su id.", description = "Obtiene un integrante del staff por su staffId")
    @GetMapping("/buscar/id/{staffId}")
    public ResponseEntity<?> getById(@PathVariable long staffId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(staffId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * delete
     * Elimina un integrante del staff por su id.
     * @param staffId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Elimina un integrante del staff por su id.", description = "Elimina un integrante del staff por su Id")
    @DeleteMapping("/eliminar/{staffId}")
    public ResponseEntity<?> delete(@PathVariable long staffId){
        try{
            adminService.delete(staffId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al integrante con Id: " + staffId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el integrante, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * update
     * Actualiza los datos de un integrante del staff por su id.
     * @param staffId
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Actualiza los datos de un integrante del staff por su id.", description = "Actualiza un integrante del staff por su id")
    @PutMapping("/actualizar/{staffId}")
    public ResponseEntity<?> update(@PathVariable long staffId, @RequestBody AdminStaffDTO entity){
        try{
            adminService.update(staffId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con Id: " + staffId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }    
}
