package com.sesi.projetos.service;

import com.sesi.projetos.model.M_Projeto;
import com.sesi.projetos.repository.R_Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class S_Projeto {
    @Autowired
    private R_Projeto r_projeto;
    public M_Projeto criarProjeto(String nome, String descricao, String codigo){
        boolean podeCriar = !nome.isEmpty() && !descricao.isEmpty() && !codigo.isEmpty();

        if (podeCriar){
            M_Projeto m_projeto = new M_Projeto();
            m_projeto.setNome(nome);
            m_projeto.setCodProjeto(codigo);
            m_projeto.setDescricao(descricao);
            return r_projeto.save(m_projeto);
        } else {
            return null;
        }
    }
}
