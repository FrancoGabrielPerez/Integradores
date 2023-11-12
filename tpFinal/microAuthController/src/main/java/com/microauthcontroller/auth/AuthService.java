package com.microauthcontroller.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microauthcontroller.jwt.JwtService;
import com.microauthcontroller.user.Role;
import com.microauthcontroller.user.User;
import com.microauthcontroller.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        //System.out.println("LoginRequest: " + request.getEmail() + " " + request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request){
        if (request.getRole() == null){
            request.setRole(Role.ADMIN);
        } 
        User user = userRepository.findByEmail(request.getEmail()).orElse(
            User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).build());
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setRole(request.getRole());
        userRepository.save(user);
       
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
}
