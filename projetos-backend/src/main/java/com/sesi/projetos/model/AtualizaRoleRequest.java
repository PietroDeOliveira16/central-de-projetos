package com.sesi.projetos.model;

import com.sesi.projetos.auth.spring_security.model.UserRole;

public class AtualizaRoleRequest {
    private String username;
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
