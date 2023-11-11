package com.microauthcontroller.auth;

import com.microauthcontroller.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;
	private String apellido;
	private String email;
	private String password;
	private long id;
	private Role role;
    
}
