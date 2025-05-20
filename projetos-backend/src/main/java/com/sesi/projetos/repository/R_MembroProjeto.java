package com.sesi.projetos.repository;

import com.sesi.projetos.model.projeto.classes.M_MembroProjeto;
import com.sesi.projetos.model.usuario.classes.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface R_MembroProjeto extends JpaRepository<M_MembroProjeto, Long> {

    @Query(value = "select * from membro_projeto where id_usuario = :id_usuario and id_projeto = :id_projeto;", nativeQuery = true)
    M_MembroProjeto findUsuarioInProjeto(@Param("id_usuario") Long id_usuario, @Param("id_projeto") Long id_projeto);
}
