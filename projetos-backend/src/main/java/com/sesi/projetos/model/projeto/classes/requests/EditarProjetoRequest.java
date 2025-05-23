package com.sesi.projetos.model.projeto.classes.requests;

import java.util.List;

public class EditarProjetoRequest {
    private String codigoProjeto;
    private List<String> cpfsMembrosDoProjeto;

    public EditarProjetoRequest(String codigoProjeto, List<String> cpfsMembrosDoProjeto) {
        this.codigoProjeto = codigoProjeto;
        this.cpfsMembrosDoProjeto = cpfsMembrosDoProjeto;
    }

    public String getCodigoProjeto() {
        return codigoProjeto;
    }

    public void setCodigoProjeto(String codigoProjeto) {
        this.codigoProjeto = codigoProjeto;
    }

    public List<String> getCpfsMembrosDoProjeto() {
        return cpfsMembrosDoProjeto;
    }

    public void setCpfsMembrosDoProjeto(List<String> cpfsMembrosDoProjeto) {
        this.cpfsMembrosDoProjeto = cpfsMembrosDoProjeto;
    }
}
