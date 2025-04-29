package com.sesi.projetos.repository;

import com.sesi.projetos.model.M_DiasEcontrosProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_DiasEncontrosProjeto extends JpaRepository<M_DiasEcontrosProjeto, Long> {
    @Query(value = "select * from dia_encontros_projeto where id_projeto = :id;",nativeQuery = true)
    List<M_DiasEcontrosProjeto> findDiasByProjetoId(@Param("id") Long id);
}
