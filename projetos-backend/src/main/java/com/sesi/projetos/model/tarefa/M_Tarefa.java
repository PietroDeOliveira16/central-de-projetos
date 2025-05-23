package com.sesi.projetos.model.tarefa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
public class M_Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime dataDeCriacao = LocalDateTime.now();
    private LocalDateTime dataDeEntrega;
    private String criadaPor;
    private String descricao;
    private String progresso;

    public M_Tarefa() {}

    public M_Tarefa(Long id, String nome, LocalDateTime dataDeCriacao, LocalDateTime dataDeEntrega, String criadaPor, String descricao, String progresso) {
        this.id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
        this.dataDeEntrega = dataDeEntrega;
        this.criadaPor = criadaPor;
        this.descricao = descricao;
        this.progresso = progresso;
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

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public LocalDateTime getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDateTime dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public String getCriadaPor() {
        return criadaPor;
    }

    public void setCriadaPor(String criadaPor) {
        this.criadaPor = criadaPor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }
}
