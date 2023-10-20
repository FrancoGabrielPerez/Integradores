package com.microuseraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microuseraccount.dto.AccountDTO;
import com.microuseraccount.service.AccountService;
import com.microuseraccount.service.UserAccountService;

import io.swagger.v3.oas.annotations.Operation;

import com.microuseraccount.model.Account;
import com.microuseraccount.model.UserAccount;


@RestController
@RequestMapping("/cuentas")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserAccountService userAccountService;

    @Operation(description = "Obtiene todas las cuentas")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(description = "Crea una cuenta")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody AccountDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(accountService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(description = "Obtiene una cuenta por su accountId")
    @GetMapping("/buscarPor/{accountId}")
    public ResponseEntity<?> getById(@PathVariable long accountId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(accountId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Elimina una cuenta por su accountId")    
    @DeleteMapping("/eliminarPor/{accountId}")
    public ResponseEntity<?> delete(@PathVariable long accountId){
        try{
            accountService.delete(accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el account, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Actualiza una cuenta por su accountId")
    @PutMapping("/actualizar/{accountId}")
    public ResponseEntity<?> update(@PathVariable long accountId, @RequestBody AccountDTO entity){
        try{
            accountService.update(accountId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Asocia un usuario a una cuenta")
    @PutMapping("/asociarUsuario/{userId}/{accountId}")
    public ResponseEntity<?> asociarUsuario(@PathVariable long userId, @PathVariable long accountId){
        try{
            accountService.asociarUsuario(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente el usuario con userId: " + userId + " a la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar el usuario a la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(description = "Desvincula un usuario de una cuenta")
    @DeleteMapping("/desvincularUsuario/{userId}/{accountId}")
    public ResponseEntity<?> desvincularUsuario(@PathVariable long userId, @PathVariable long accountId){
        try{
            accountService.desvincularUsuario(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente el usuario con userId: " + userId + " de la cuenta con accountId: " + accountId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular el usuario de la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/saldo/obtenerSaldo/{accountId}")
    public ResponseEntity<?> getSaldo(@PathVariable long accountId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userAccountService.getSaldo(accountId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/saldo/actualizarSaldo/{accountId}")
    public void updateSaldo(@PathVariable long accountId, @RequestBody Double saldo) {
        userAccountService.updateSaldo(saldo, accountId);
    }

    /* @GetMapping("genero/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/ordenadosPorApellidoYNombre")
    public ResponseEntity<?> getAllSorted(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findAllByOrderByApellidoAscNombreAsc());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/buscarPor")
    public ResponseEntity<?> buscarPor(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.accountPorCiudadDeResidencia(carrera, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } */

}
