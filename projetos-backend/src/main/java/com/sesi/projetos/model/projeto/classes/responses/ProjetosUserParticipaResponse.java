package com.sesi.projetos.model.projeto.classes.responses;

import com.sesi.projetos.model.projeto.interfaces.I_ProjetosParticipando;

import java.time.LocalDateTime;

public class ProjetosUserParticipaResponse {
    private String nome;
    private String codProjeto;
    private LocalDateTime dataInscricao;

    public ProjetosUserParticipaResponse(String nome, String codProjeto, LocalDateTime dataInscricao) {
        this.nome = nome;
        this.codProjeto = codProjeto;
        this.dataInscricao = dataInscricao;
    }

    public ProjetosUserParticipaResponse(I_ProjetosParticipando projeto) {
        this.nome = projeto.getNome();
        this.codProjeto = projeto.getcodProjeto();
        this.dataInscricao = projeto.getdataInscricao();
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

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
