package com.sesi.projetos.controller;

import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.CadastroRequest;
import com.sesi.projetos.service.S_Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class C_Usuario {
    private final S_Usuario s_usuario;

    public C_Usuario(S_Usuario s_usuario) {
        this.s_usuario = s_usuario;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> postCadastrarUsuario(@RequestBody CadastroRequest usuarioApi){
        return s_usuario.cadastrarUsuario(usuarioApi);
    }

    @PostMapping("/login")
    public ResponseEntity<String> postLogin(@RequestBody LoginRequest loginRequest){
        return s_usuario.login(loginRequest);
    }
}