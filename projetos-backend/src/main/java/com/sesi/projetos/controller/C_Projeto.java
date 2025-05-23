package com.sesi.projetos.controller;

import com.sesi.projetos.model.projeto.classes.*;
import com.sesi.projetos.model.projeto.classes.requests.CriarProjetoRequest;
import com.sesi.projetos.model.projeto.classes.requests.EditarProjetoRequest;
import com.sesi.projetos.model.projeto.classes.responses.GetProjetosSafeResponse;
import com.sesi.projetos.model.projeto.classes.responses.ProjetoApi;
import com.sesi.projetos.model.projeto.classes.responses.ProjetosUserParticipaResponse;
import com.sesi.projetos.service.S_Projeto;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/admin/getProjetosSafe")
    public List<GetProjetosSafeResponse> getProjetsSafe(){
        return s_projeto.getProjetosSafe();
    }

    @GetMapping("/getProjetos")
    public List<ProjetoApi> getProjetos(){
        return s_projeto.getProjetosApi();
    }

    @PostMapping("/getDiasProjeto")
    public ResponseEntity<String> postDiasProjetos(@RequestBody String codigoProjeto){
        return s_projeto.getDiasProjeto(codigoProjeto);
    }

    @PostMapping("/admin/editarProjeto")
    public ResponseEntity<String> postEditarProjeto(@RequestBody EditarProjetoRequest editarProjetoRequest){
        return s_projeto.editarProjeto(editarProjetoRequest);
    }

    @GetMapping("/getProjetosParticipando")
    public List<ProjetosUserParticipaResponse> getProjetosParicipando(HttpServletRequest request){
        return s_projeto.getProjetosUsuarioParticipa(request);
    }

    @PostMapping("/acharProjetoCodigo")
    public M_Projeto postAcharProjetoCodigo(@RequestBody String codigo, HttpServletRequest request){
        return s_projeto.acharProjetoPorCodigo(codigo, request);
    }
}
