package com.sesi.projetos.service;

import com.sesi.projetos.model.tarefa.M_Tarefa;
import com.sesi.projetos.repository.R_Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S_Tarefa {
    @Autowired
    private R_Tarefa r_tarefa;
    public List<M_Tarefa> getAllTarefas() {
        return r_tarefa.findAll();
    }
}
