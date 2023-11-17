package com.microauthcontroller.auth;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.register(request, token));
    }

    @DeleteMapping(value = "/eliminar/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email, @RequestHeader("Authorization") String token) {
        // Call the service method to delete the user
        authService.deleteUser(email, token);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    /**
     * validar
     * Comprueba la validez de un token.
     * @param token
     * @return
     */
    @PostMapping(value = "/validar")
    public ResponseEntity<?> validar(@RequestBody String token) {
        String response;
        try {
            response = authService.validar(token);
            if (response == null) {
                return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", response);
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
        }
    }
}
