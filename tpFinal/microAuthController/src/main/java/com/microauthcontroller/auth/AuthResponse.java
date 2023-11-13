package com.microauthcontroller.auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthResponse
 * Clase que representa la respuesta de autenticación.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private String token;
}
