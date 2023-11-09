package com.microuseraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * getAll
     * Obtiene todas las cuentas.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todos las cuentas.", description = "Obtiene todas las cuentas")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
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
    public ResponseEntity<?> getCuentasByUserId(@PathVariable long userId){
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
    public ResponseEntity<?> save(@RequestBody AccountDTO entity){
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
    public ResponseEntity<?> getById(@PathVariable long accountId) {
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
    public ResponseEntity<?> delete(@PathVariable long accountId){
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
    public ResponseEntity<?> suspend(@PathVariable long accountId){
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
    public ResponseEntity<?> activate(@PathVariable long accountId){
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
    public ResponseEntity<?> update(@PathVariable long accountId, @RequestBody AccountDTO entity){
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
    public ResponseEntity<?> asociarUsuario(@PathVariable long userId, @PathVariable long accountId){
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
    public ResponseEntity<?> desvincularUsuario(@PathVariable long userId, @PathVariable long accountId){
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
    public ResponseEntity<?> getSaldo(@PathVariable long accountId) {
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
    public void updateSaldo(@PathVariable long accountId, @RequestBody Double saldo) {
        accountService.updateSaldo(saldo, accountId);
    }
}
