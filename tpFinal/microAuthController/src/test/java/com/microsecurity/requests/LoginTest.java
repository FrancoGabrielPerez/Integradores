package com.microsecurity.requests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.microauthcontroller.auth.AuthService;
import com.microauthcontroller.auth.LoginRequest;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

public class LoginTest {
    @Autowired
    AuthService authService;

    @Test
    public void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        authService.login(loginRequest);
        
    }
}
