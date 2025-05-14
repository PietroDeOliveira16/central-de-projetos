package com.sesi.projetos.service;

import com.sesi.projetos.auth.jwt.service.JwtService;
import com.sesi.projetos.auth.spring_security.model.UserRole;
import com.sesi.projetos.auth.util.SecurityParameters;
import com.sesi.projetos.model.AtualizaRoleRequest;
import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.M_Usuario;
import com.sesi.projetos.model.CadastroRequest;
import com.sesi.projetos.repository.R_Usuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private JwtService jwtService;

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
            m_usuario.setRole(UserRole.ALUNO);
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

    public ResponseEntity<String> login(LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                    );
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(loginRequest.getUsername());
                response.addCookie(cookieCreation("sessionToken", token,
                        "/", true, true, SecurityParameters.TOKEN_COOKIE_INT_MAX_AGE_SECS, false));
                response.addCookie(cookieCreation("sessionCookie", "sessionlogged",
                        "/", false, true, SecurityParameters.TOKEN_COOKIE_INT_MAX_AGE_SECS, false));
                return ResponseEntity.ok("Login efetuado com sucesso!");
            }
        }catch (Exception e){
            return ResponseEntity.ok("Erro ao logar, verifique suas informações e tente novamente.");
        }
        return ResponseEntity.ok("Erro desconhecido :(");
    }

    private Cookie cookieCreation(String name, String value, String path, boolean httpOnly, boolean secure, int maxAge, boolean sameSite){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        if(!sameSite){
            cookie.setAttribute("SameSite", "None");
        }
        return cookie;
    }

    public ResponseEntity<String> atualizarRole(AtualizaRoleRequest atualizaRoleRequest) {
        M_Usuario usuario = r_usuario.findByUsername(atualizaRoleRequest.getUsername());
        if(usuario.getRole() == atualizaRoleRequest.getRole()){
            return ResponseEntity.ok("Esse usuário já possui este cargo.");
        }
        try{
            r_usuario.updateUserRole(usuario.getUsername(), atualizaRoleRequest.getRole());
            return ResponseEntity.ok("Cargo de " + usuario.getUsername() + " alterado com sucesso.");
        } catch (Exception e){
            return ResponseEntity.ok("Erro interno do banco de dados. Tente novamente mais tarde.");
        }
    }
}