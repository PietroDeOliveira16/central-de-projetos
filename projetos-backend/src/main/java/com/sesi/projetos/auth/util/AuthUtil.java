package com.sesi.projetos.auth.util;

import com.sesi.projetos.auth.jwt.service.S_Jwt;
import com.sesi.projetos.model.usuario.classes.M_Usuario;
import com.sesi.projetos.repository.R_Usuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AuthUtil {

    public static String retreiveTokenFromCookies(Cookie[] cookies){
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    public static M_Usuario findUsuarioWithRequestCookies(HttpServletRequest request, S_Jwt jwtService, R_Usuario userRepository){
        String token = AuthUtil.retreiveTokenFromCookies(request.getCookies());
        return userRepository.findByUsername(jwtService.extractUsernameFromToken(token));
    }
}
