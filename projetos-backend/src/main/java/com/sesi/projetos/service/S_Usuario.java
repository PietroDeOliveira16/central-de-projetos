package com.sesi.projetos.service;

import com.sesi.projetos.model.LoginRequest;
import com.sesi.projetos.model.M_Usuario;
import com.sesi.projetos.model.CadastroRequest;
import com.sesi.projetos.repository.R_Usuario;
import com.sesi.projetos.util.BCryptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {
    @Autowired
    private R_Usuario r_usuario;

    public ResponseEntity<String> cadastrarUsuario(CadastroRequest usuarioApi) {
        boolean podeCadastrar = !usuarioApi.getNome().isEmpty() &&
                !usuarioApi.getUsername().isEmpty() && !usuarioApi.getCpf().isEmpty() &&
                !usuarioApi.getPassword().isEmpty();

        if(podeCadastrar){
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setUsername(usuarioApi.getUsername());
            //m_usuario.setPassword(usuarioApi.getPassword());
            m_usuario.setPassword(BCryptConfig.bCryptPasswordEncoder().encode(usuarioApi.getPassword()));
            m_usuario.setNome(usuarioApi.getNome());
            m_usuario.setTelefone(usuarioApi.getTelefone());
            m_usuario.setEmail(usuarioApi.getEmail());
            m_usuario.setCpf(usuarioApi.getCpf());
            try{
                r_usuario.save(m_usuario);
                return ResponseEntity.ok("Usuário cadastrado");
            }catch (Exception e){
                return ResponseEntity.ok("Erro ao cadastrar usuario: " + e);
            }
        } else {
            return ResponseEntity.ok("Verifique se todas as informações estão corretas");
        }
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        return null;
    }
}