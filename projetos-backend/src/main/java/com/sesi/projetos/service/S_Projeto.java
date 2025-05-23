package com.sesi.projetos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesi.projetos.auth.jwt.service.S_Jwt;
import com.sesi.projetos.auth.spring_security.model.UserRole;
import com.sesi.projetos.model.projeto.classes.*;
import com.sesi.projetos.model.projeto.interfaces.I_ProjetosParticipando;
import com.sesi.projetos.model.projeto.interfaces.I_ProjetosSafe;
import com.sesi.projetos.model.usuario.classes.M_Usuario;
import com.sesi.projetos.repository.R_DiasEncontrosProjeto;
import com.sesi.projetos.repository.R_MembroProjeto;
import com.sesi.projetos.repository.R_Projeto;
import com.sesi.projetos.repository.R_Usuario;
import com.sesi.projetos.util.CookieHandler;
import com.sesi.projetos.util.ValidadorDeCpf;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class S_Projeto {
    @Autowired
    private R_Projeto r_projeto;
    @Autowired
    private R_Usuario r_usuario;
    @Autowired
    private R_DiasEncontrosProjeto r_diasEncontrosProjeto;
    @Autowired
    private R_MembroProjeto r_membroProjeto;
    @Autowired
    private S_Jwt s_jwt;

    public ResponseEntity<String> criarProjeto(String nome, String descricao, String codigo, String dados) {
        boolean podeCriar = !nome.isEmpty() && !descricao.isEmpty() && !codigo.isEmpty();

        if (podeCriar) {
            if (descricao.length() > 2000) {
                return ResponseEntity.ok("Descrição ultrapassa o limite de caracteres!");
            }
            codigo = codigo.replaceAll("\\s+", "").toUpperCase();
            if (r_projeto.findProjetoByCodigo(codigo) != null) {
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

    public List<ProjetoApi> getProjetosApi() {
        List<M_Projeto> projetos = r_projeto.findAll();
        List<ProjetoApi> projetosApi = new ArrayList<>();
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
            ProjetoApi projetoApi = new ProjetoApi(String.valueOf(projeto.getId()), projeto.getNome(), projeto.getDescricao(), projeto.getCodProjeto(), dias, horarios);
            projetosApi.add(projetoApi);
        }
        return projetosApi;
    }

    public ResponseEntity<String> getDiasProjeto(String codigoProjeto) {
        StringBuilder response = new StringBuilder();
        List<M_DiasEcontrosProjeto> diasEncontro = r_diasEncontrosProjeto.findDiasByProjetoId(
                r_projeto.findProjetoByCodigo(codigoProjeto).getId());
        M_DiasEcontrosProjeto horarioDosDias = diasEncontro.get(0);
        for (M_DiasEcontrosProjeto dias : diasEncontro) {
            response.append(dias.getDiaDaSemana()).append(";");
        }
        response.append("/");
        response.append(horarioDosDias.getDataEncontroInicio()).append(";");
        response.append(horarioDosDias.getDataEncontroTermino()).append(";");
        return ResponseEntity.ok(response.toString());
    }

    public List<GetProjetosSafeResponse> getProjetosSafe() {
        List<I_ProjetosSafe> projetosSafesInterface = r_projeto.findProjetosInterface();
        List<GetProjetosSafeResponse> projetosResponse = new ArrayList<>();
        for (I_ProjetosSafe projeto : projetosSafesInterface) {
            projetosResponse.add(new GetProjetosSafeResponse(projeto.getNome(), projeto.getcodProjeto()));
        }

        return projetosResponse;
    }

    public ResponseEntity<String> editarProjeto(EditarProjetoRequest editarProjetoRequest) {
        if (!editarProjetoRequest.getCodigoProjeto().isBlank()) {
            M_Projeto projeto = r_projeto.findProjetoByCodigo(editarProjetoRequest.getCodigoProjeto());
            List<M_MembroProjeto> membrosDoProjeto = new ArrayList<>();
            for (String cpf : editarProjetoRequest.getCpfsMembrosDoProjeto()) {
                if (cpf.isBlank()) {
                    continue;
                }
                // VALIDADOR DE CPF TEMPORARIAMENTE COMENTADO PARA TESTES
                /*if (!ValidadorDeCpf.validarCpf(cpf)) {
                    return ResponseEntity.ok("CPF " + cpf + " não é um CPF válido / existente. Verifique se ele está escrito corretamente ou possui o tamanho correto de um CPF (11 números)");
                }*/
                M_MembroProjeto membroProjeto = new M_MembroProjeto();
                M_Usuario membro = new M_Usuario();
                try {
                    membro = r_usuario.findByCpf(cpf);
                } catch (Exception e) {
                    return ResponseEntity.ok("CPF " + cpf + " não está registrado no sistema");
                }
                if (membro != null) {
                    if (membro.getRole() == UserRole.PROFESSOR || membro.getRole() == UserRole.ALUNO) {
                        if (r_membroProjeto.findUsuarioInProjeto(membro.getId(), projeto.getId()) != null) {
                            if (membro.getRole() == UserRole.PROFESSOR) {
                                return ResponseEntity.ok("Professor do CPF " + membro.getCpf() + " já é responsável por este projeto");
                            } else if (membro.getRole() == UserRole.ALUNO) {
                                return ResponseEntity.ok("Aluno do CPF " + membro.getCpf() + " já participa deste projeto");
                            }
                        }
                        membroProjeto.setUsuario(membro);
                        membroProjeto.setProjeto(projeto);
                        membrosDoProjeto.add(membroProjeto);
                    } else {
                        return ResponseEntity.ok("O usuário do CPF " + membro.getCpf() + " não possui cadastro de professor ou de aluno");
                    }
                } else {
                    return ResponseEntity.ok("Usuário do CPF " + cpf + " não foi encontrado no sistema");
                }
            }
            if (!membrosDoProjeto.isEmpty()) {
                r_membroProjeto.saveAll(membrosDoProjeto);
                return ResponseEntity.ok("Edição do projeto " + projeto.getNome() + " concluída com sucesso!");
            } else {
                return ResponseEntity.ok("Nenhuma edição foi feita ao projeto");
            }
            //return ResponseEntity.ok("Edição do projeto " + projeto.getNome() + " concluída com sucesso!");
        } else {
            return ResponseEntity.ok("Selecione o projeto para edição");
        }
    }

    public List<ProjetosUserParticipaResponse> getProjetosUsuarioParticipa(HttpServletRequest request) {
        String token = CookieHandler.retreiveTokenFromCookies(request.getCookies());
        M_Usuario usuario = r_usuario.findByUsername(s_jwt.extractUsernameFromToken(token));
        List<I_ProjetosParticipando> projetosUserParticipando = r_projeto.findProjetosUserParticipaById(usuario.getId());
        List<ProjetosUserParticipaResponse> response = new ArrayList<>();
        for(I_ProjetosParticipando projeto : projetosUserParticipando){
            response.add(new ProjetosUserParticipaResponse(projeto));
        }
        return response;
    }
}
