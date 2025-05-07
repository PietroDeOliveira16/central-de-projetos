package com.sesi.projetos.auth.util;

import java.util.Arrays;
import java.util.List;

public class SecurityParameters {
    public static final int ENCODER_STRENGTH = 12;
    public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/auth/login",
            "/auth/cadastro",
            "/projeto/getProjetos",
            "/projeto/getDiasProjeto"
    );
    public static final long TOKEN_COOKIE_LONG_MAX_AGE_SECS = (24L * 60L * 60L);
    public static final int TOKEN_COOKIE_INT_MAX_AGE_SECS = (24 * 60 * 60);
    public static final long TOKEN_COOKIE_MAX_AGE_MILIS = (TOKEN_COOKIE_LONG_MAX_AGE_SECS * 1000);
}
