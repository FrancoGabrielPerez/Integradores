package com.microapigateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * JwtUtils
 * Se encarga de generar y validar los tokens.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    /**
     * initKey
     * Inicializa la clave secreta.
     */
    @PostConstruct
    public void initKey() {
        byte[] custKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(custKey);
    }

    /**
     * getClaims
     * Obtiene los claims del token.
     * @param token
     * @return Claims
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * generateToken
     * Genera el token.
     * @param subject
     * @return String
     */
    public boolean isExpired(String token) {
        try {           
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            // System.out.println("Error: " + e);
            return true;
        }
    }
    
    // Siguientes lineas para comprobar roles si es necesario.
    public enum Role {
        ADMIN, MAINTENER;
    }
    
    /**
     * isRoleInvalid
     * Verifica si el rol es inválido.
     * @param token
     * @param role
     * @return
     */
    public boolean isRoleInvalid(String token, String role) {
        try {
             return !Role.valueOf(getClaims(token).get("role", String.class)).equals(Role.valueOf(role));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * getClaims
     * Obtiene los claims del token.
     * @param token
     * @param type
     * @return <T> T
     */
    public <T> T getClaims(String token, Function<Claims, T> type) {
        final Claims claims = getAllClaimsFromToken(token);
        return type.apply(claims);
    }

    /**
     * getAllClaimsFromToken
     * Obtiene los claims del token.
     * @param token
     * @return Claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * getRoleFromToken
     * Obtiene el rol del token.
     * @param token
     * @return String
     */
    public String getRoleFromToken(String token) {
        return getClaims(token, claims -> claims.get("role", String.class));
    }   
}
