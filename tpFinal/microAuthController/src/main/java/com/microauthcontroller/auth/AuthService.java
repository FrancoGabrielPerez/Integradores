package com.microauthcontroller.auth;

import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microauthcontroller.jwt.JwtService;
import com.microauthcontroller.user.Role;
import com.microauthcontroller.user.User;
import com.microauthcontroller.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * AuthService
 * Se encarga de procesar los pedidos de login y registro de usuarios.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    /**
     * login
     * Procesa los pedidos de login.
     * @param request
     * @return AuthResponse
     */
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

    /**
     * register
     * Procesa los pedidos de registro.
     * @param request
     * @return AuthResponse
     */
    public AuthResponse register(RegisterRequest request, String token){
        //System.out.println("Auth RegisterRequest: " +request.getId()+ " " + request.getEmail() + " " + request.getPassword() + ", ROLE:" + request.getRole() + " " + request.getNombre() + " " + request.getApellido());
        if (request.getRole() == null){
            request.setRole(Role.USER);
        }
        if (request.getRole() != Role.USER && !validar(token).equals("ADMIN")) {
            throw new RuntimeException("No tiene permisos para registrar un usuario con ese rol");
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

    @Transactional
    public void deleteUser(String email, String token) {
        if (token == null) {
            throw new IllegalArgumentException("Invalid token");
        }
        String authorities = validar(token);
        if (!authorities.contains("ADMIN")) {
            throw new IllegalArgumentException("Insufficient permissions");
        }
        UserDetails user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.deleteByEmail(user.getUsername());
    }

    /**
     * validar
     * Valida el token recibido.
     * @param token
     * @return String
     */
	public String validar(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.replaceFirst("Bearer ", "");
        }
        String username = jwtService.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("UserDetails: " + userDetails);
		if (jwtService.validateToken(token, userDetails)){
            return userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "));
        }
        return null;
	}
}
