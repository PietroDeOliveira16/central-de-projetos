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
import org.apache.coyote.Response;
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

    public ResponseEntity<String> criarProjeto(String nome, String descricao, String codigo, String dados) {
        boolean podeCriar = !nome.isEmpty() && !descricao.isEmpty() && !codigo.isEmpty();
        boolean continuarCriacao = false;

        if (podeCriar) {
            if (descricao.length() > 2000) {
                return ResponseEntity.ok("Descrição ultrapassa o limite de caracteres!");
            }
            codigo = codigo.replaceAll("\\s+", "").toUpperCase();
            if(r_projeto.findProjetoByCodigo(codigo) != null){
                return ResponseEntity.ok("Código de projeto já foi escolhido, por favor escolha outro códgio.");
            } else if (codigo.length() > 5) {
                return ResponseEntity.ok("Código do projeto ultrapassa o limite de caracteres!");
            } else if (codigo.length() < 3) {
                return ResponseEntity.ok("Insira um código maior para o projeto! (Mínimo de 3 caracteres, máximo de 5)");
            }
            M_Projeto m_projeto = new M_Projeto();
            m_projeto.setNome(nome);
            m_projeto.setCodProjeto(codigo);
            m_projeto.setDescricao(descricao);
            try {
                r_projeto.save(m_projeto);
                return criarProjetoDias(dados, m_projeto.getCodProjeto());
            } catch (DataIntegrityViolationException exception) {
                return ResponseEntity.ok("Erro interno do banco de dados ao salvar projeto.");
            }
        } else {
            return ResponseEntity.ok("Certifique-se de que todos os campos estão corretamente preenchidos.");
        }
    }

    public ResponseEntity<String> criarProjetoDias(String dadosObject, String codigo) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> diasSemana = Arrays.asList("segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira");

        List<String> dias = new ArrayList<>();
        List<String> horarios = new ArrayList<>();

        dadosObject = dadosObject.replaceAll("[\\[\\]']", ""); // Remove colchetes e aspas simples
        String[] dados = dadosObject.split(",\\s*"); // Divide pelos elementos

        try {
            for (String valor : dados) {
                if (diasSemana.contains(valor.toLowerCase())) {
                    dias.add(valor);
                } else if (valor.matches("\\d{2}:\\d{2}")) {
                    horarios.add(valor);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean podeSalvar = !dias.isEmpty() && !horarios.isEmpty() && !codigo.isEmpty();

        if (podeSalvar) {
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
                return ResponseEntity.ok("Projeto criado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.ok("Erro interno ao registrar os dias dos encontros");
            }
        } else {
            return ResponseEntity.ok("Confira se todas as informações estão corretas");
        }
    }

    public List<Projeto_Api> getProjetosApi() {
        List<M_Projeto> projetos = r_projeto.findAll();
        List<Projeto_Api> projetosApi = new ArrayList<>();
        for (M_Projeto projeto : projetos) {
            List<M_DiasEcontrosProjeto> m_diasEcontrosProjetos = r_diasEncontrosProjeto.findDiasByProjetoId(projeto.getId());
            List<String> dias = new ArrayList<>();
            for (M_DiasEcontrosProjeto dia :
                    m_diasEcontrosProjetos) {
                dias.add(dia.getDiaDaSemana());
            }
            List<LocalTime> horarios = new ArrayList<>();
            try {
                horarios.add(m_diasEcontrosProjetos.get(0).getDataEncontroInicio());
                horarios.add(m_diasEcontrosProjetos.get(1).getDataEncontroTermino());
            } catch (Exception ex) {
                horarios = null;
            }
            Projeto_Api projetoApi = new Projeto_Api(String.valueOf(projeto.getId()), projeto.getNome(), projeto.getDescricao(), projeto.getCodProjeto(), dias, horarios);
            projetosApi.add(projetoApi);
        }
        return projetosApi;
    }

    public ResponseEntity<String> getDiasProjeto(String codigoProjeto) {
        StringBuilder response = new StringBuilder();
        List<M_DiasEcontrosProjeto> diasEncontro = r_diasEncontrosProjeto.findDiasByProjetoId(
                r_projeto.findProjetoByCodigo(codigoProjeto).getId());
        M_DiasEcontrosProjeto horarioDosDias = diasEncontro.get(0);
        for (M_DiasEcontrosProjeto dias : diasEncontro){
            response.append(dias.getDiaDaSemana()).append(";");
        }
        response.append("/");
        response.append(horarioDosDias.getDataEncontroInicio()).append(";");
        response.append(horarioDosDias.getDataEncontroTermino()).append(";");
        return ResponseEntity.ok(response.toString());
    }
}
