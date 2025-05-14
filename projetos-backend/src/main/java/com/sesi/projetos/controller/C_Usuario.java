package com.sesi.projetos.controller;

import com.sesi.projetos.auth.spring_security.model.UserRole;
import com.sesi.projetos.model.AtualizaRoleRequest;
import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.CadastroRequest;
import com.sesi.projetos.service.S_Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
    public ResponseEntity<String> postLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        return s_usuario.login(loginRequest, response);
    }

    @PostMapping("/admin/atualizarRole")
    public ResponseEntity<String> postAtualizarRole(@RequestBody AtualizaRoleRequest atualizaRoleRequest) {
        return s_usuario.atualizarRole(atualizaRoleRequest);
    }
}