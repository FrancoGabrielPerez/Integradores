package com.microadministration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microadministration.service.MaintenanceService;

/**
 * MaintenanceAppController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Mantenimiento.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("/mantenimiento")
public class MaintenanceAppController {
    
    @Autowired
    private MaintenanceService maintenanceService;

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
     * actualizarEstado()
     * Actualiza el estado de un monopatin.
     * @param id
     * @param estado
     * @return
     */
    @PutMapping("/monopatines/actualizarEstado/{id}/estado/{estado}")
    public ResponseEntity<?> actualizarEstado(@PathVariable long id, @PathVariable String estado){
        try{
            maintenanceService.updateScooterState(id, estado);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el estado del scooter: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el estado del scooter, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
