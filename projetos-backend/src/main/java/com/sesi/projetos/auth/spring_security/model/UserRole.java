package com.sesi.projetos.auth.spring_security.model;

public enum UserRole {
    ADMIN("admin"),
    PROFESSOR("professor"),
    ALUNO("aluno");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
