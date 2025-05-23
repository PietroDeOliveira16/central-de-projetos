package com.sesi.projetos.model.projeto.classes.responses;

import java.time.LocalTime;
import java.util.List;

public class ProjetoApi {
    private String id;
    private String nomeProjeto;
    private String descricaoProjeto;
    private String codigoProjeto;
    private List<String> diasEncontros;
    private List<LocalTime> horarios;

    public ProjetoApi() {
    }

    public ProjetoApi(String id, String nomeProjeto, String descricaoProjeto, String codigoProjeto, List<String> diasEncontros, List<LocalTime> horarios) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.codigoProjeto = codigoProjeto;
        this.diasEncontros = diasEncontros;
        this.horarios = horarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getDiasEncontros() {
        return diasEncontros;
    }

    public void setDiasEncontros(List<String> diasEncontros) {
        this.diasEncontros = diasEncontros;
    }

    public List<LocalTime> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<LocalTime> horarios) {
        this.horarios = horarios;
    }
}
