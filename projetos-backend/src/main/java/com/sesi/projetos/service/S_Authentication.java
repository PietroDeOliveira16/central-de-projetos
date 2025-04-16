package com.sesi.projetos.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class S_Authentication {
    private final S_Jwt s_jwt;

    public S_Authentication(S_Jwt s_jwt) {
        this.s_jwt = s_jwt;
    }

    public String authenticate(Authentication authentication){
        return s_jwt.generateToken(authentication);
    }
}
