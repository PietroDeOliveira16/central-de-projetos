package com.sesi.projetos.model.projeto.classes;

import com.sesi.projetos.model.usuario.classes.M_Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "membro_projeto")
public class M_MembroProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private M_Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private M_Projeto projeto;

    private LocalDateTime dataInscricao = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public M_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(M_Usuario usuario) {
        this.usuario = usuario;
    }

    public M_Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(M_Projeto projeto) {
        this.projeto = projeto;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
