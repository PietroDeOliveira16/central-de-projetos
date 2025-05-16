package com.sesi.projetos.model;

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
}
