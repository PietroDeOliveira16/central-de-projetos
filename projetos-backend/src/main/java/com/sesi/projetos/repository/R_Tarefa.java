package com.sesi.projetos.repository;

import com.sesi.projetos.model.tarefa.M_Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Tarefa extends JpaRepository<M_Tarefa, Long> {
}
