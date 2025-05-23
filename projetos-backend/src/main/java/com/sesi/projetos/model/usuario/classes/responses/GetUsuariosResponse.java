package com.sesi.projetos.model.usuario.classes.responses;

import com.sesi.projetos.model.usuario.interfaces.I_UsuarioSafe;

import java.util.ArrayList;
import java.util.List;

public class GetUsuariosResponse {
    private Long id;
    private String nome;
    private String role;

    public GetUsuariosResponse(Long id, String nome, String role) {
        this.id = id;
        this.nome = nome;
        if(role.equals("0")){
            role = "ADMIN";
        } else if (role.equals("1")) {
            role = "PROFESSOR";
        } else if (role.equals("2")){
            role = "ALUNO";
        } else {
            role = "";
        }
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static List<GetUsuariosResponse> getIUsuarioConverter(List<I_UsuarioSafe> usuarioSafes){
        List<GetUsuariosResponse> response = new ArrayList<>();
        for(I_UsuarioSafe usuarioSafe : usuarioSafes){
            response.add(new GetUsuariosResponse(usuarioSafe.getId(), usuarioSafe.getNome(), usuarioSafe.getRole()));
        }
        return response;
    }
}
