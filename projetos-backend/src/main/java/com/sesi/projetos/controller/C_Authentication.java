package com.sesi.projetos.controller;

import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.LoginResponse;
import com.sesi.projetos.service.S_Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C_Authentication {
    private final S_Authentication s_authentication;

    public C_Authentication(S_Authentication s_authentication) {
        this.s_authentication = s_authentication;
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<LoginResponse> postAuthenticate(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(new LoginResponse(s_authentication.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        )));
    }
}
