package com.sesi.projetos.service;

import com.sesi.projetos.model.LoginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class S_Authentication {
    private final S_Jwt s_jwt;
    private final S_UserDetails s_userDetails;

    public S_Authentication(S_Jwt s_jwt, S_UserDetails s_userDetails) {
        this.s_jwt = s_jwt;
        this.s_userDetails = s_userDetails;
    }

    public ResponseEntity<?> authenticate(Authentication authentication, HttpServletResponse response){
        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.addCookie(gerarCookieRefreshToken(s_jwt.generateRefreshToken(authentication)));

        try {
            String accessToken = s_jwt.generateToken(authentication);

            return ResponseEntity.ok(Map.of("accessToken", accessToken));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não conseguiu gerar o token de acesso");
        }
    }

    public ResponseEntity<?> reAuthenticateAccessToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("refreshToken".equals(cookie.getName())){
                    String refreshToken = cookie.getValue();
                    if (s_jwt.validateToken(refreshToken)) {
                        String username = s_jwt.getUsernameFromToken(refreshToken);
                        UserDetails userDetails = s_userDetails.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        String newAccessToken = s_jwt.generateToken(authentication);
                        String newRefreshToken = s_jwt.generateRefreshToken(authentication);
                        response.addCookie(gerarCookieRefreshToken(newRefreshToken));
                        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido ou expirado");
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private Cookie gerarCookieRefreshToken(String refreshToken){
        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/auth/refresh");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        return refreshCookie;
    }

    public boolean validateToken(String token){
        return s_jwt.validateToken(token);
    }

    public String getUsernameFromToken(String token){
        return s_jwt.getUsernameFromToken(token);
    }
}
