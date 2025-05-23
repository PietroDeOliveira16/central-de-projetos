package com.sesi.projetos.controller;

import com.sesi.projetos.model.tarefa.M_Tarefa;
import com.sesi.projetos.service.S_Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tarefa")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class C_Tarefa {
    @Autowired
    private S_Tarefa s_tarefa;

    @GetMapping("/getTarefas")
    public List<M_Tarefa> getTarefas(){
        return s_tarefa.getAllTarefas();
    }
}
