package com.microtravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microtravel.dto.TravelDTO;
import com.microtravel.dto.FareDTO;
import com.microtravel.service.TravelService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * TravelController
 * 
 * Controlador de la entidad Travel.
 * @Author Luciano Melluso, Franco Perez, Lautaro Liuzzi, Ruben Marchiori
 */
@RestController
@RequestMapping("/viajes")
public class TravelController {
    
    @Autowired
    private TravelService travelService;

    // URL del servicio de validación de tokens
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8081/auth/validar";

    /**
     * validarToken
     * Valida el token antes de realizar cualquier operación.
     * @param token
     */
    private ResponseEntity<String> validarToken(String token) {
        // Realizar una llamada al servicio de validación de tokens
        // System.out.println("Validando token: " + token);
        ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_VALIDATION_URL, token, String.class);
        // System.out.println("Respuesta: " + response);
        return response;
    }


    /**
     * getAll
     * Obtiene todos los viajes.
     * @return
     */
    @Operation(summary = "Obtiene todos los viajes.", description = "Obtiene todos los viajes")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * Agrega un viaje.
     * @param entity
     * @return
     */
    @Operation(summary = "Agrega un viaje.", description = "Agrega un viaje")
    @PostMapping("/alta/usuario/{idUsuario}/scooter/{idScooter}")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @PathVariable long idUsuario, @PathVariable long idScooter) {
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(travelService.save(idUsuario, idScooter));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * getById
     * Obtiene un viaje por su id.
     * @param travelId
     * @return
     */
    @Operation(summary = "Obtiene una estacion viaje por su id.", description = "Obtiene un viaje por su travelId")
    @GetMapping("/buscar/{travelId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable long travelId) {
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.findById(travelId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * delete
     * Elimina un viaje por su id.
     * @param travelId
     * @return
     */
    @Operation(summary = "Eliminia un viaje por su id.", description = "Elimina un viaje por su travelId")
    @DeleteMapping("/eliminar/{travelId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long travelId){
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            travelService.delete(travelId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con travelId: " + travelId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * update
     * Actualiza los datos de un viaje por su id.
     * @param travelId
     * @param entity
     * @return
     */
    @Operation(summary = "Actualiza los datos de un viaje por su id.", description = "Actualiza los datos de un viaje por su travelId")
    @PutMapping("/actualizar/{travelId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable long travelId, @RequestBody TravelDTO entity){
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            travelService.update(travelId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del viaje con travelId: " + travelId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 

    /**
     * travelEnd
     * Finaliza un viaje por su id.
     * @param travelId
     * @return
     */
    @Operation(summary = "Finaliza un viaje por su id.", description = "Finaliza un viaje por su travelId")
    @PutMapping("/finalizar/{travelId}")
    public ResponseEntity<?> travelEnd(@RequestHeader("Authorization") String token, @PathVariable long travelId){
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            travelService.travelEnd(travelId);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente el viaje con travelId: " + travelId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar el viaje, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    /**
     * saveFare
     * Guarda una tarifa nueva a aplicar desde la fecha dada.
     * @param entity
     * @return
     */
    @Operation(summary = "Guarda una tarifa nueva a aplicar desde la fecha dada.", description = "Guarda una tarifa nueva a aplicar desde la fecha dada")   
    @PostMapping("/tarifas/alta")
    public ResponseEntity<?> saveFare(@RequestHeader("Authorization") String token, @RequestBody FareDTO entity) {
        ResponseEntity<String> response = validarToken(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(travelService.saveFare(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }   
}
