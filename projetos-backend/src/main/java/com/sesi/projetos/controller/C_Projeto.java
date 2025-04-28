package com.sesi.projetos.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sesi.projetos.model.M_Projeto;
import com.sesi.projetos.model.Projeto_Api;
import com.sesi.projetos.service.S_Projeto;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class C_Projeto {
    private final S_Projeto s_projeto;

    public C_Projeto(S_Projeto s_projeto) {
        this.s_projeto = s_projeto;
    }

    @PostMapping("/criarProjeto")
    public ResponseEntity<String> postCriar(@RequestBody Projeto_Api projetoApi) {
        return s_projeto.criarProjeto(projetoApi.getNomeProjeto(),
                projetoApi.getDescricaoProjeto(), projetoApi.getCodigoProjeto());
    }

    @PostMapping("/criarProjeto/dias")
    public ResponseEntity<String> postCriarDias(@RequestBody String dados){
        return s_projeto.criarProjetoDias(dados);
    }

    @GetMapping("/getProjetos")
    public List<Projeto_Api> postGetProjetos(){
        return s_projeto.getProjetosApi();
    }
}
