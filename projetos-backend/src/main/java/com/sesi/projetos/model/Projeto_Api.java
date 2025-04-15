package com.sesi.projetos.model;

public class Projeto_Api {
    private String nomeProjeto;
    private String descricaoProjeto;
    private String codigoProjeto;

    public Projeto_Api() {
    }

    public Projeto_Api(String nomeProjeto, String descricaoProjeto, String codigoProjeto) {
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.codigoProjeto = codigoProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getCodigoProjeto() {
        return codigoProjeto;
    }

    public void setCodigoProjeto(String codigoProjeto) {
        this.codigoProjeto = codigoProjeto;
    }
}
