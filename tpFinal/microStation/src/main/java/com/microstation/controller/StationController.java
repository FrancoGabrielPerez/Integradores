package com.microstation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microstation.dto.StationDTO;
import com.microstation.service.StationService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * StationController
 * 
 * Clase que contiene los endpoints de la API.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("/estaciones")
public class StationController {
    
    @Autowired
    private StationService stationService;

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
     * Obtiene todas las estaciones.
     * @return ResponseEntity
     */
    @Operation(summary = "Obtiene todas las estaciones.", description = "Obtiene todos las estaciones")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * Agrega una estacion.
     * @param entity
     */
    @Operation(summary = "Agrega una estacion.", description = "Agrega una estacion")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token,@RequestBody StationDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }

        try{            
            return ResponseEntity.status(HttpStatus.OK).body(stationService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * Obtiene una estacion por su id.
     * @param stationId
     * @return ResponseEntity
     */
    @Operation(summary = "Obtiene una estacion por su id.", description = "Obtiene un estacion por su stationId")
    @GetMapping("/buscar/{stationId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token,@PathVariable String stationId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findById(stationId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * Elimina una estacion por su id.
     * @param stationId
     * @return ResponseEntity
     */
    @Operation(summary = "Eliminia una estacion por su id.", description = "Elimina una estacion por su stationId")
    @DeleteMapping("/eliminar/{stationId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,@PathVariable String stationId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            stationService.delete(stationId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la estacion con stationId: " + stationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * Actualiza los datos de una estacion por su id.
     * @param stationId
     * @param entity
     * @return ResponseEntity
     */
    @Operation(summary = "Actualiza los datos de una estacion por su id.", description = "Actualiza una estacion por su stationId")
    @PutMapping("/actualizar/{stationId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@PathVariable String stationId, @RequestBody StationDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }

        try{
            stationService.update(stationId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos de la estacion con stationId: " + stationId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
   
    /**
     * Verifica si las coordenadas pertenecen a una estacion valida.
     * @param latitud
     * @param longitud
     * @return ResponseEntity
     */
    @Operation(summary = "Verifica si las coordenadas son validas.", description = "Verifica que las coordenadas proveidas coinciden con als coordenadas de una estacion")
    @GetMapping("/verificar/latitud/{latitud}/longitud/{longitud}")
    public ResponseEntity<?> verify(@RequestHeader("Authorization") String token,@PathVariable String latitud, @PathVariable String longitud){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findByLatitudAndLongitud(latitud, longitud));
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo verificar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
