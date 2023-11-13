package com.microapigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microapigateway.services.JwtUtils;

import lombok.RequiredArgsConstructor;

/**
 * GatewayController
 * Se encarga de validar los tokens de autenticación.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class GatewayController {

    private final JwtUtils jwtUtils;

    /**
     * validar
     * Valida los tokens de autenticación. Utiliza la clase JwtUtils del microservicio
     * microApiGateway.
     * @param request
     * @return
     */
    @PostMapping(value = "/validar")
    public ResponseEntity<?> validar(@RequestBody String request) {
        try {
            // System.out.println("Request: " + request);
            String token = request.substring(7);
            // System.out.println("Token: " + token);
            if (!jwtUtils.isExpired(token))
                return ResponseEntity.ok("Token válido");
            return ResponseEntity.badRequest().body("Token inválido");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }   
}
