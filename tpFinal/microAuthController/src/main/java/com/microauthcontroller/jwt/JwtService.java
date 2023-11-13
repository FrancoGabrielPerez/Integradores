package com.microauthcontroller.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

/**
 * JwtService
 * Se encarga de generar y validar los tokens.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String SECRET_KEY = "pepeArgentoErroriojsonwebtokesecuritySignatureExceptionJWTpepeArgentoErroriojsonwebtokesecuritySignatureExceptionJWT";

    /**
     * getToken
     * Setea los extraClaims y llama al metodo del mismo nombre para generar el token.
     * @param user
     * @return String
     */
    public String getToken(UserDetails user) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getAuthorities().stream().findFirst().get().getAuthority());
        return getToken(new HashMap<>(), user);             
    }

    /**
     * getToken
     * Genera el token.
     * @param extraClaims
     * @param user
     * @return String
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();           
    }

    /**
     * getKey
     * Decodifica la clave secreta.
     * @return Key
     */
    private Key getKey() {
        byte[] decodedKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    /**
     * getUsernameFromToken
     * Obtiene el username del token.
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    /**
     * validateToken
     * Valida el token. Si el username del token es igual al username del usuario y el token no expiro devuelve true.
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * getAllClaimsFromToken
     * Obtiene todos los claims del token.
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * getClaims
     * Obtiene los claims del token.
     * @param <T>
     * @param token
     * @param type
     * @return
     */
    public <T> T getClaims(String token, Function<Claims, T> type) {
        final Claims claims = getAllClaimsFromToken(token);
        return type.apply(claims);
    }

    /**
     * getExpiration
     * Obtiene la fecha de expiracion del token.
     * @param token
     * @return
     */
    private Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    /**
     * isTokenExpired
     * Verifica si el token expiro.
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }
}
