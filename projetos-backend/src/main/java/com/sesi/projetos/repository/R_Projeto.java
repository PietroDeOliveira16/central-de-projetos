package com.sesi.projetos.repository;

import com.sesi.projetos.model.M_Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Projeto extends JpaRepository<M_Projeto, Long> {

}
