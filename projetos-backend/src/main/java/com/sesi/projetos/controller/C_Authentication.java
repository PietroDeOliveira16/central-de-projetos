package com.sesi.projetos.controller;

import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.LoginResponse;
import com.sesi.projetos.service.S_Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class C_Authentication {
    private final S_Authentication s_authentication;

    public C_Authentication(S_Authentication s_authentication) {
        this.s_authentication = s_authentication;
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<?> postAuthenticate(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        return s_authentication.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()),
                response);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> postRefreshToken(HttpServletRequest request, HttpServletResponse response){
        return s_authentication.reAuthenticateAccessToken(request, response);
    }
}
