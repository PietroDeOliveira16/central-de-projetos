package com.sesi.projetos.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class S_Jwt {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public S_Jwt(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 5L; //3600L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).
                collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("projetos-backend")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 604800L; // 7 dias

        var claims = JwtClaimsSet.builder()
                .issuer("projetos-backend")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
        try {
            decoder.decode(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return decoder.decode(token).getSubject();
    }
}
