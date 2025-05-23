package com.sesi.projetos.util;

import jakarta.servlet.http.Cookie;

public class CookieHandler {
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
}
