package com.microapigateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void initKey() {
        byte[] custKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(custKey);
    }

    public Claims getClaims(String token) {
        Claims claimsBody = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claimsBody;
    }

    public boolean isExpired(String token) {
        try {           
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return true;
        }
    }
    
    
    public enum Role {
        ADMIN, MAINTENER;
    }
    
    public boolean isRoleInvalid(String token, String role) {
        try {
             return !Role.valueOf(getClaims(token).get("role", String.class)).equals(Role.valueOf(role));
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T getClaims(String token, Function<Claims, T> type) {
        final Claims claims = getAllClaimsFromToken(token);
        return type.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String getRoleFromToken(String token) {
        return getClaims(token, claims -> claims.get("role", String.class));
    }   
}
