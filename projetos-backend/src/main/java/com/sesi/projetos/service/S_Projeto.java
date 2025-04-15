package com.sesi.projetos.service;

import com.sesi.projetos.model.M_Projeto;
import com.sesi.projetos.model.Projeto_Api;
import com.sesi.projetos.repository.R_Projeto;
import org.hibernate.mapping.SortableValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class S_Projeto {
    @Autowired
    private R_Projeto r_projeto;
    public ResponseEntity<String> criarProjeto(String nome, String descricao, String codigo){
        boolean podeCriar = !nome.isEmpty() && !descricao.isEmpty() && !codigo.isEmpty();

        if (podeCriar){
            M_Projeto m_projeto = new M_Projeto();
            m_projeto.setNome(nome);
            m_projeto.setCodProjeto(codigo);
            m_projeto.setDescricao(descricao);
            try {
                r_projeto.save(m_projeto);
                return ResponseEntity.ok("Projeto criado com sucesso!");
            } catch (DataIntegrityViolationException exception){
                return ResponseEntity.ok("O código escolhido já existe, por favor escolha outro código.");
            }
        } else {
            return ResponseEntity.ok("Certifique-se de que todos os campos estão corretamente preenchidos.");
        }
    }

    public List<Projeto_Api> getProjetosApi(){
        List<M_Projeto> projetos = r_projeto.findAll();
        List<Projeto_Api> projetosApi = new ArrayList<>();
        for(M_Projeto projeto : projetos){
            Projeto_Api projetoApi = new Projeto_Api(projeto.getNome(), projeto.getDescricao(), projeto.getCodProjeto());
            projetosApi.add(projetoApi);
        }
        return projetosApi;
    }
}
