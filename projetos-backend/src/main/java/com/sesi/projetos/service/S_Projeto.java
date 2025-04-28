package com.sesi.projetos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesi.projetos.model.M_DiasEcontrosProjeto;
import com.sesi.projetos.model.M_Projeto;
import com.sesi.projetos.model.Projeto_Api;
import com.sesi.projetos.repository.R_DiasEncontrosProjeto;
import com.sesi.projetos.repository.R_Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class S_Projeto {
    @Autowired
    private R_Projeto r_projeto;
    @Autowired
    private R_DiasEncontrosProjeto r_diasEncontrosProjeto;

    public ResponseEntity<String> criarProjeto(String nome, String descricao, String codigo){
        boolean podeCriar = !nome.isEmpty() && !descricao.isEmpty() && !codigo.isEmpty();

        if (podeCriar){
            if(descricao.length() > 2000){
                return ResponseEntity.ok("Descrição ultrapassa o limite de caracteres!");
            }
            codigo = codigo.replaceAll("\\s+", "").toUpperCase();
            if(codigo.length() > 5) {
                return ResponseEntity.ok("Código do projeto ultrapassa o limite de caracteres!");
            } else if (codigo.length() < 3){
                return ResponseEntity.ok("Insira um código maior para o projeto! (Mínimo de 3 caracteres, máximo de 5)");
            }
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

    public ResponseEntity<String> criarProjetoDias(String dados){
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> diasSemana = Arrays.asList("segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado", "domingo");

        List<String> dias = new ArrayList<>();
        List<String> horarios = new ArrayList<>();
        String codigo = "";

        try{
            List<String> valores = objectMapper.readValue(dados, new TypeReference<List<String>>() {});
            for(String valor : valores){
                if (diasSemana.contains(valor.toLowerCase())) {
                    dias.add(valor);
                } else if (valor.matches("\\d{2}:\\d{2}")) {
                    horarios.add(valor);
                } else {
                    codigo = valor;
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        boolean podeSalvar = !dias.isEmpty() && !horarios.isEmpty() && !codigo.isEmpty();

        if(podeSalvar){
            try {
                List<M_DiasEcontrosProjeto> diasEcontrosProjetos = new ArrayList<>();
                for (String dia : dias) {
                    M_DiasEcontrosProjeto diasEncontro = new M_DiasEcontrosProjeto();
                    diasEncontro.setDiaDaSemana(dia);
                    diasEncontro.setDataEncontroInicio(LocalTime.parse(horarios.get(0)));
                    diasEncontro.setDataEncontroTermino(LocalTime.parse(horarios.get(1)));
                    diasEncontro.setProjeto(r_projeto.findProjetoByCodigo(codigo));
                    diasEcontrosProjetos.add(diasEncontro);
                }
                r_diasEncontrosProjeto.saveAll(diasEcontrosProjetos);
                return ResponseEntity.ok("Dias dos encontros registrados");
            }catch (Exception e){
                return ResponseEntity.ok("Erro interno ao registrar os dias dos encontros");
            }
        } else {
            return ResponseEntity.ok("Confira se todas as informações estão corretas");
        }
    }

    public List<Projeto_Api> getProjetosApi(){
        List<M_Projeto> projetos = r_projeto.findAll();
        List<Projeto_Api> projetosApi = new ArrayList<>();
        for(M_Projeto projeto : projetos){
            List<M_DiasEcontrosProjeto> m_diasEcontrosProjetos = r_diasEncontrosProjeto.findDiasByProjetoId(projeto.getId());
            List<String> dias = new ArrayList<>();
            for (M_DiasEcontrosProjeto dia:
                 m_diasEcontrosProjetos) {
                dias.add(dia.getDiaDaSemana());
            }
            List<LocalTime> horarios = new ArrayList<>();
            horarios.add(m_diasEcontrosProjetos.get(0).getDataEncontroInicio());
            horarios.add(m_diasEcontrosProjetos.get(1).getDataEncontroTermino());
            Projeto_Api projetoApi = new Projeto_Api(String.valueOf(projeto.getId()), projeto.getNome(), projeto.getDescricao(), projeto.getCodProjeto(), dias, horarios);
            projetosApi.add(projetoApi);
        }
        return projetosApi;
    }
}
