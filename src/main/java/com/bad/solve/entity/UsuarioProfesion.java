package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO_PROFESION")
public class UsuarioProfesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USU_PROF")
    private Long codUsuProf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USU", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    @Column(name = "PROFESION", length = 100)
    private String profesion;

    public Long getCodUsuProf() {
        return codUsuProf;
    }

    public void setCodUsuProf(Long codUsuProf) {
        this.codUsuProf = codUsuProf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
