package com.sesi.projetos.model;

public class CriarProjetoRequest {
    private ProjetoApi projetoApi;
    private Object dados;

    public ProjetoApi getProjetoApi() {
        return projetoApi;
    }

    public void setProjetoApi(ProjetoApi projetoApi) {
        this.projetoApi = projetoApi;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
