package com.sesi.projetos.repository;

import com.sesi.projetos.model.M_Projeto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Projeto extends JpaRepository<M_Projeto, Long> {
    @Query(value = "select * from projeto where cod_projeto = :codigoProjeto;", nativeQuery = true)
    M_Projeto findProjetoByCodigo(@Param("codigoProjeto") String codigoProjeto);

    @Transactional
    @Modifying
    @Query(value = "delete from projeto where cod_projeto = :codigoProjeto;", nativeQuery = true)
    void deleteProjetoWithCodigo(@Param("codigoProjeto") String codigoProjeto);
}
