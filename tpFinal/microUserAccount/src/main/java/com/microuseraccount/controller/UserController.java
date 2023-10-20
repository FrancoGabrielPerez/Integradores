package com.microuseraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microuseraccount.dto.UserDTO;
import com.microuseraccount.service.UserService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Operation(description = "Obtiene todos los usuarios")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(description = "Crea un usuario")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody UserDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(description = "Obtiene un usuario por su userId")
    @GetMapping("/buscarPor/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Elimina un usuario por su userId")
    @DeleteMapping("/eliminarPor/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId){
        try{
            userService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Actualiza un usuario por su userId")
    @PutMapping("/actualizar/{userId}")
    public ResponseEntity<?> update(@PathVariable long userId, @RequestBody UserDTO entity){
        try{
            userService.update(userId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Asocia una cuenta a un usuario")
    @PutMapping("/asociarCuenta/{userId}/{accountId}")
    public ResponseEntity<?> asociarCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            userService.asociarCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente la cuenta con accountId: " + accountId + " al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Desvincula una cuenta de un usuario")
    @DeleteMapping("/desvincularCuenta/{userId}/{accountId}")
    public ResponseEntity<?> desvincularCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            userService.desvincularCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente la cuenta con accountId: " + accountId + " del usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /* @GetMapping("genero/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/ordenadosPorApellidoYNombre")
    public ResponseEntity<?> getAllSorted(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAllByOrderByApellidoAscNombreAsc());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @GetMapping("/buscarPor")
    public ResponseEntity<?> buscarPor(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.userPorCiudadDeResidencia(carrera, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } */

}
