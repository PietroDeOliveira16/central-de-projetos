package com.sesi.projetos.auth.util;

import java.util.Arrays;
import java.util.List;

public class SecurityParameters {
    public static final int ENCODER_STRENGTH = 12;
    public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/auth/**",
            "/projeto/getProjetos",
            "/projeto/getDiasProjeto"
    );
}
