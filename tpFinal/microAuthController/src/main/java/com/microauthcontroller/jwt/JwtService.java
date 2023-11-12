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

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String SECRET_KEY = "pepeArgentoErroriojsonwebtokesecuritySignatureExceptionJWTpepeArgentoErroriojsonwebtokesecuritySignatureExceptionJWT";

    public String getToken(UserDetails user) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getAuthorities().stream().findFirst().get().getAuthority());
        return getToken(new HashMap<>(), user);             
    }

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

    private Key getKey() {
        byte[] decodedKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaims(String token, Function<Claims, T> type) {
        final Claims claims = getAllClaimsFromToken(token);
        return type.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }
}
