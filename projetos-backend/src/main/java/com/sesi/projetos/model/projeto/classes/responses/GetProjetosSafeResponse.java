package com.sesi.projetos.model.projeto.classes.responses;

import java.util.List;

public class GetProjetosSafeResponse {
    private String nome;
    private String codProjeto;

    public GetProjetosSafeResponse(String nome, String codProjeto) {
        this.nome = nome;
        this.codProjeto = codProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodProjeto() {
        return codProjeto;
    }

    public void setCodProjeto(String codProjeto) {
        this.codProjeto = codProjeto;
    }
}
