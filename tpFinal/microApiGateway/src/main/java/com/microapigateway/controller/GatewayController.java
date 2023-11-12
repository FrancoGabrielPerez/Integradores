package com.microapigateway.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microapigateway.services.JwtUtils;

import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper; // Jackson's JSON parser

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class GatewayController {

    private final JwtUtils jwtUtils;


    @PostMapping(value = "/validar")
    public ResponseEntity<?> validar(@RequestBody String request) {
        try {
            System.out.println("Request: " + request);
            String token = request.substring(7);
            // ObjectMapper mapper = new ObjectMapper();
            // Map<String, String> map = mapper.readValue(request, Map.class);
            //String token = map.get("token");

            System.out.println("Token: " + token);
            if (!jwtUtils.isExpired(token))
                return ResponseEntity.ok("Token válido");
            return ResponseEntity.badRequest().body("Token inválido");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }
   
}
