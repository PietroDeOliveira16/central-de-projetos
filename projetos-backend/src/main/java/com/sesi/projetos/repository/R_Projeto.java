package com.sesi.projetos.repository;

import com.sesi.projetos.model.projeto.classes.M_Projeto;
import com.sesi.projetos.model.projeto.interfaces.I_ProjetosParticipando;
import com.sesi.projetos.model.projeto.interfaces.I_ProjetosSafe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_Projeto extends JpaRepository<M_Projeto, Long> {
    @Query(value = "select * from projeto where cod_projeto = :codigoProjeto;", nativeQuery = true)
    M_Projeto findProjetoByCodigo(@Param("codigoProjeto") String codigoProjeto);

    @Transactional
    @Modifying
    @Query(value = "delete from projeto where cod_projeto = :codigoProjeto;", nativeQuery = true)
    void deleteProjetoWithCodigo(@Param("codigoProjeto") String codigoProjeto);

    @Query(value = "select nome, cod_projeto from projeto;", nativeQuery = true)
    List<I_ProjetosSafe> findProjetosInterface();

    @Query(value = "select p.nome, p.cod_projeto, m.data_inscricao from projeto p " +
            "join membro_projeto m on m.id_usuario = :id_usuario and p.id = m.id_projeto;", nativeQuery = true)
    List<I_ProjetosParticipando> findProjetosUserParticipaById(@Param("id_usuario") Long idUsuario);

    @Query(value = "select p.* from projeto p " +
            "join membro_projeto m on m.id_usuario = :id_usuario and p.id = :id_projeto limit 1;", nativeQuery = true)
    M_Projeto findIfUserIsFromProjeto(@Param("id_projeto") Long idProjeto, @Param("id_usuario") Long idUsuario);
}
