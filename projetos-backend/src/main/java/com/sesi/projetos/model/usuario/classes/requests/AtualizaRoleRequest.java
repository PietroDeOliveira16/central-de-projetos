package com.sesi.projetos.model.usuario.classes.requests;

import com.sesi.projetos.auth.spring_security.model.UserRole;

public class AtualizaRoleRequest {
    private Long id;
    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
