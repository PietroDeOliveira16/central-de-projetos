package com.sesi.projetos.repository;

import com.sesi.projetos.model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
    M_Usuario findByUsername(String username);
}