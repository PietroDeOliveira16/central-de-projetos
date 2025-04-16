package com.sesi.projetos.service;

import com.sesi.projetos.repository.R_Usuario;
import com.sesi.projetos.util.auth.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class S_UserDetails implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private R_Usuario r_usuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return r_usuario.findUserByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
