package com.sesi.projetos.repository;

import com.sesi.projetos.auth.spring_security.model.UserRole;
import com.sesi.projetos.model.M_Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
    M_Usuario findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update usuario set role = :role where username = :username;", nativeQuery = true)
    void updateUserRole(String username, UserRole role);
}