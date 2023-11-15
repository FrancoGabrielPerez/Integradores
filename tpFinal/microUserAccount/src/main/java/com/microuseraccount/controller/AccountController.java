package com.microuseraccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microuseraccount.dto.AccountDTO;
import com.microuseraccount.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * AccountController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de cuentas.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@RestController
@RequestMapping("/cuentas")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    // URL del servicio de validación de tokens
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8082/auth/validar";

    /**
     * validarToken
     * Valida el token antes de realizar cualquier operación.
     * @param token
     */
    private ResponseEntity<String> validarToken(String token, List<String> roles) {
        ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_VALIDATION_URL, token, String.class);
        if (response.getStatusCode() != HttpStatus.OK){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        if(!(roles.contains(response.getBody()))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tipo de usuario no valido");
        }
        return response;
    }


    /**
     * getAll
     * Obtiene todas las cuentas.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todos las cuentas.", description = "Obtiene todas las cuentas")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getCuentasByUserId
     * Obtiene todas las cuentas de un usuario.
     * @param userId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todas las cuentas de un usuario.", description = "Obtiene todas las cuentas de un usuario")
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> getCuentasByUserId(@RequestHeader("Authorization") String token, @PathVariable long userId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getCuentasByUserId(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * Crea una nueva cuenta.
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Crea una nueva cuenta.", description = "Crea una cuenta")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody AccountDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(accountService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * getById
     * Obtiene una cuenta por su id.
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene una cuenta por su id.", description = "Obtiene una cuenta por su accountId")
    @GetMapping("/buscar/{accountId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable long accountId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(accountId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * delete
     * Elimina una cuenta por su id.
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Elimina una cuenta por su id.", description = "Elimina una cuenta por su accountId")    
    @DeleteMapping("/eliminar/{accountId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long accountId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.delete(accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el account, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * suspend
     * Suspende tempooralmente una cuenta por su id.
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Desactiva una cuenta por su id.", description = "Desactiva una cuenta por su accountId")
    @PutMapping("/suspender/{accountId}")
    public ResponseEntity<?> suspend(@RequestHeader("Authorization") String token, @PathVariable long accountId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.suspendAccount(accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se suspendio correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo suspender la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * activate
     * Activa una cuenta por su id.
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Activa una cuenta por su id.", description = "Activa una cuenta por su accountId")
    @PutMapping("/activar/{accountId}")
    public ResponseEntity<?> activate(@RequestHeader("Authorization") String token, @PathVariable long accountId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.activateAccount(accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se activo correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo activar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * update
     * Actualiza una cuenta por su id.
     * @param accountId
     * @param entity
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Actualiza una cuenta por su id.", description = "Actualiza una cuenta por su accountId")
    @PutMapping("/actualizar/{accountId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable long accountId, @RequestBody AccountDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.update(accountId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * asociarUsuario
     * Vincula un usuario a una cuenta.
     * @param userId
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Vincula una cuenta a un usuario.", description = "Vincula un usuario a una cuenta")
    @PutMapping("/vincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> asociarUsuario(@RequestHeader("Authorization") String token, @PathVariable long userId, @PathVariable long accountId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.asociarUsuario(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente el usuario con userId: " + userId + " a la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar el usuario a la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * desvincularUsuario
     * Desvincula un usuario de una cuenta.
     * @param userId
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Desvincula una cuenta de un usuario.", description = "Desvincula un usuario de una cuenta")
    @DeleteMapping("/desvincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> desvincularUsuario(@RequestHeader("Authorization") String token, @PathVariable long userId, @PathVariable long accountId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.desvincularUsuario(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente el usuario con userId: " + userId + " de la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular el usuario de la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getSaldo
     * Obtiene el saldo de una cuenta.
     * @param accountId
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene el saldo de una cuenta.", description = "Obtiene el saldo de una cuenta")
    @GetMapping("/saldo/obtener/{accountId}")
    public ResponseEntity<?> getSaldo(@RequestHeader("Authorization") String token, @PathVariable long accountId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getSaldo(accountId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * updateSaldo
     * Actualiza el saldo de una cuenta.
     * @param accountId
     * @param saldo
     */
    @Operation(summary = "Actualiza el saldo de una cuenta.", description = "Actualiza el saldo de una cuenta")
    @PutMapping("/saldo/actualizar/{accountId}")
    public ResponseEntity<?> updateSaldo(@RequestHeader("Authorization") String token, @PathVariable long accountId, @RequestBody Double saldo) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            accountService.updateSaldo(saldo, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Saldo Actualizado!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
