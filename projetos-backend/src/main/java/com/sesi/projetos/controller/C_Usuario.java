package com.sesi.projetos.controller;

import com.sesi.projetos.model.Usuario_Api;
import com.sesi.projetos.service.S_Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class C_Usuario {
    private final S_Usuario s_usuario;

    public C_Usuario(S_Usuario s_usuario) {
        this.s_usuario = s_usuario;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> postCadastrarUsuario(@RequestBody Usuario_Api usuarioApi){
        return s_usuario.cadastrarUsuario(usuarioApi);
    }


}