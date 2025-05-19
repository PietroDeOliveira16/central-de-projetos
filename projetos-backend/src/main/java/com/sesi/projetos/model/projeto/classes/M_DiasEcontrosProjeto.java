package com.sesi.projetos.model.projeto.classes;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "dia_encontros_projeto")
public class M_DiasEcontrosProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false)
    private M_Projeto projeto;

    private String diaDaSemana;

    private LocalTime dataEncontroInicio;
    private LocalTime dataEncontroTermino;

    public M_DiasEcontrosProjeto() {}

    public M_DiasEcontrosProjeto(M_Projeto projeto, LocalTime dataEncontroInicio) {
        this.projeto = projeto;
        this.dataEncontroInicio = dataEncontroInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public M_Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(M_Projeto projeto) {
        this.projeto = projeto;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getDataEncontroInicio() {
        return dataEncontroInicio;
    }

    public void setDataEncontroInicio(LocalTime dataEncontroInicio) {
        this.dataEncontroInicio = dataEncontroInicio;
    }

    public LocalTime getDataEncontroTermino() {
        return dataEncontroTermino;
    }

    public void setDataEncontroTermino(LocalTime dataEncontroTermino) {
        this.dataEncontroTermino = dataEncontroTermino;
    }
}
