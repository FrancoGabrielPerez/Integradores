package com.microsecurity.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsecurity.dto.AdminStaffDTO;
import com.microsecurity.dto.AuthRequestDTO;
import com.microsecurity.security.jwt.JWTFilter;
import com.microsecurity.security.jwt.TokenProvider;
import com.microsecurity.service.AdminStaffService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api" )
@RequiredArgsConstructor
public class UserResource {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AdminStaffService adminStaffService;


    // INICIAR SESION
    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate( @Valid @RequestBody AuthRequestDTO request ) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = tokenProvider.createToken (authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AdminStaffDTO> register( @Valid @RequestBody AdminStaffDTO request ){
        System.out.println("llego al register");
        final var newUser = this.adminStaffService.createUser( request );
        return new ResponseEntity<>( newUser, HttpStatus.CREATED );
    }

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}
