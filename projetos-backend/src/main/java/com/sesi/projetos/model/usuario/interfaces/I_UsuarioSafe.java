package com.sesi.projetos.model.usuario.interfaces;

import com.sesi.projetos.auth.spring_security.model.UserRole;

public interface I_UsuarioSafe {
    Long getId();
    String getNome();
    String getRole();
}
