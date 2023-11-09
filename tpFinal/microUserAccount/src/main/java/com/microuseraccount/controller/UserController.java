package com.microuseraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microuseraccount.dto.UserDTO;
import com.microuseraccount.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * UserController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de usuarios.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * getAll
     * Obtiene todos los usuarios.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todos los usuarios.", description = "Obtiene todos los usuarios")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * Crea un nuevo usuario.
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Agrega un nuevo usuario.", description = "Crea un usuario")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody UserDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * getById
     * Obtiene un usuario por su id.
     * @param userId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un usuario por su id.", description = "Obtiene un usuario por su userId")
    @GetMapping("/buscar/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * delete
     * Elimina un usuario por su id.
     * @param userId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Elimina un usuario por su id.", description = "Elimina un usuario por su userId")
    @DeleteMapping("/eliminar/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId){
        try{
            userService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * update
     * Actualiza los datos de un usuario por su id.
     * @param userId
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Actualiza los datos de un usuario por su id.", description = "Actualiza un usuario por su userId")
    @PutMapping("/actualizar/{userId}")
    public ResponseEntity<?> update(@PathVariable long userId, @RequestBody UserDTO entity){
        try{
            userService.update(userId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * asociarCuenta
     * Vincula una cuenta a un usuario.
     * @param userId
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Vincula una cuenta a un usuario.", description = "Vincula una cuenta a un usuario")
    @PutMapping("/vincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> asociarCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            userService.asociarCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente la cuenta con accountId: " + accountId + " al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * desvincularCuenta
     * Desvincula una cuenta de un usuario.
     * @param userId
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Desvincula una cuenta de un usuario.", description = "Desvincula una cuenta de un usuario")
    @DeleteMapping("/desvincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> desvincularCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            userService.desvincularCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente la cuenta con accountId: " + accountId + " del usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
