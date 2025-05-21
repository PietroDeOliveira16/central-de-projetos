package com.sesi.projetos.auth.spring_security.service;

import com.sesi.projetos.auth.spring_security.model.UserPrincipal;
import com.sesi.projetos.model.usuario.classes.M_Usuario;
import com.sesi.projetos.repository.R_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private R_Usuario r_usuario;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        M_Usuario usuario = r_usuario.findByUsername(username);

        if(usuario == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(usuario);
    }
}
