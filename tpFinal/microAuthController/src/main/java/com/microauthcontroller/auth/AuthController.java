package com.microauthcontroller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * AuthController
 * Se encarga de enrutar las peticiones a los microservicios correspondientes.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * login
     * Enruta las peticiones login al servicio correspondiente.
     * @param request
     * @return
     */
    @PostMapping(value = "/acceder")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * register
     * Enruta las peticiones registro al servicio correspondiente.
     * @param request
     * @return
     */
    @PostMapping(value = "/registrar")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }  
    
    @PostMapping(value = "/validar")
    public ResponseEntity<?> validar(@RequestBody String request) {
        String response = authService.validar(request);
        if (response == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
