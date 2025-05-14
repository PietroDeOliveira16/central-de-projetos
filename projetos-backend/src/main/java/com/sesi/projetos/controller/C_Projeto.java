package com.sesi.projetos.controller;

import com.sesi.projetos.model.CriarProjetoRequest;
import com.sesi.projetos.model.ProjetoApi;
import com.sesi.projetos.service.S_Projeto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projeto")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class C_Projeto {
    private final S_Projeto s_projeto;

    public C_Projeto(S_Projeto s_projeto) {
        this.s_projeto = s_projeto;
    }

    @PostMapping("/admin/criarProjeto")
    public ResponseEntity<String> postCriar(@RequestBody CriarProjetoRequest criarProjetoRequest) {
        return s_projeto.criarProjeto(criarProjetoRequest.getProjetoApi().getNomeProjeto(),
                criarProjetoRequest.getProjetoApi().getDescricaoProjeto(), criarProjetoRequest.getProjetoApi().getCodigoProjeto(), criarProjetoRequest.getDados().toString());
    }

    @GetMapping("/getProjetos")
    public List<ProjetoApi> postGetProjetos(){
        return s_projeto.getProjetosApi();
    }

    @PostMapping("/getDiasProjeto")
    public ResponseEntity<String> postDiasProjetos(@RequestBody String codigoProjeto){
        return s_projeto.getDiasProjeto(codigoProjeto);
    }
}
