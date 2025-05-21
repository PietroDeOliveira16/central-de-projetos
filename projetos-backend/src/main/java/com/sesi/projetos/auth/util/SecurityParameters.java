package com.sesi.projetos.auth.util;

import java.util.Arrays;
import java.util.List;

public class SecurityParameters {
    public static final int ENCODER_STRENGTH = 12;
    public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/auth/login",
            "/auth/cadastro",
            "/auth/getUsuarios",
            "/projeto/getProjetos",
            "/projeto/getDiasProjeto"
    );
    public static final List<String> ADMIN_POST_ENDPOINTS = Arrays.asList(
            "/projeto/admin/criarProjeto",
            "/projeto/admin/editarProjeto",
            "/auth/admin/atualizarRole"
    );
    public static final List<String> ADMIN_GET_ENDPOINTS = Arrays.asList(
            "/projeto/admin/getProjetosSafe"
    );
    public static final List<String> PROFESSOR_POST_ENDPOINTS = Arrays.asList(

    );
    public static final List<String> PROFESSOR_GET_ENDPOINTS = Arrays.asList(

    );
    public static final List<String> ALUNO_POST_ENDPOINTS = Arrays.asList(

    );
    public static final List<String> ALUNO_GET_ENDPOINTS = Arrays.asList(

    );
    public static final long TOKEN_COOKIE_LONG_MAX_AGE_SECS = (24L * 60L * 60L);
    public static final int TOKEN_COOKIE_INT_MAX_AGE_SECS = (24 * 60 * 60);
    public static final long TOKEN_COOKIE_MAX_AGE_MILIS = (TOKEN_COOKIE_LONG_MAX_AGE_SECS * 1000);
}
