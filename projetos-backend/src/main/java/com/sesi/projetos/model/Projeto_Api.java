package com.sesi.projetos.model;

public class Projeto_Api {
    private String nomeProjeto;
    private String descricaoProjeto;
    private String codigoProjeto;
    private String dataCriacao;

    public Projeto_Api() {
    }

    public Projeto_Api(String nomeProjeto, String descricaoProjeto, String codigoProjeto, String dataCriacao) {
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.codigoProjeto = codigoProjeto;
        this.dataCriacao = dataCriacao;
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

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
