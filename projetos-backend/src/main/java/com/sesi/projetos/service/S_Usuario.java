package com.sesi.projetos.service;

import com.sesi.projetos.auth.jwt.service.JWTService;
import com.sesi.projetos.auth.util.SecurityParameters;
import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.M_Usuario;
import com.sesi.projetos.model.CadastroRequest;
import com.sesi.projetos.repository.R_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {
    @Autowired
    private R_Usuario r_usuario;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(SecurityParameters.ENCODER_STRENGTH);

    public ResponseEntity<String> cadastrarUsuario(CadastroRequest usuarioApi) {
        boolean podeCadastrar = !usuarioApi.getNome().isEmpty() &&
                !usuarioApi.getUsername().isEmpty() && !usuarioApi.getCpf().isEmpty() &&
                !usuarioApi.getPassword().isEmpty();

        if (podeCadastrar) {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setUsername(usuarioApi.getUsername());
            m_usuario.setPassword(usuarioApi.getPassword());
            m_usuario.setPassword(passwordEncoder.encode(usuarioApi.getPassword()));
            m_usuario.setNome(usuarioApi.getNome());
            m_usuario.setTelefone(usuarioApi.getTelefone());
            m_usuario.setEmail(usuarioApi.getEmail());
            m_usuario.setCpf(usuarioApi.getCpf());
            try {
                r_usuario.save(m_usuario);
                return ResponseEntity.ok("Usuário cadastrado");
            } catch (Exception e) {
                return ResponseEntity.ok("Erro ao cadastrar usuario: " + e);
            }
        } else {
            return ResponseEntity.ok("Verifique se todas as informações estão corretas");
        }
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                );
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok("Login feito com sucesso! Token: " + jwtService.generateToken(loginRequest.getUsername()));
        }

        return ResponseEntity.ok("Erro ao tentar logar.");
    }
}