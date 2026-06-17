package com.bad.solve.entity;

import com.bad.solve.id.UsuarioEncuestaId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO_ENCUESTA")
public class UsuarioEncuesta {

    @EmbeddedId
    private UsuarioEncuestaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codEnc")
    @JoinColumn(name = "COD_ENC", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Encuesta encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codUsu")
    @JoinColumn(name = "COD_USU", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    public UsuarioEncuestaId getId() {
        return id;
    }

    public void setId(UsuarioEncuestaId id) {
        this.id = id;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
