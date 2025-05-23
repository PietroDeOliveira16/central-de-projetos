package com.sesi.projetos.controller;

import com.sesi.projetos.model.auth.CadastroRequest;
import com.sesi.projetos.model.auth.LoginRequest;
import com.sesi.projetos.model.usuario.classes.requests.AtualizaRoleRequest;
import com.sesi.projetos.model.usuario.classes.responses.GetUsuariosResponse;
import com.sesi.projetos.service.S_Usuario;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUsuarios")
    public List<GetUsuariosResponse> getUsuarios(){
        return s_usuario.getUsuarios();
    }
}