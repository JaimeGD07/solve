package com.bad.solve.entity;

import com.bad.solve.id.IdentificaId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "IDENTIFICA")
public class Identifica {

    @EmbeddedId
    private IdentificaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codRol")
    @JoinColumn(name = "COD_ROL", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codUsu")
    @JoinColumn(name = "COD_USU", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    public IdentificaId getId() {
        return id;
    }

    public void setId(IdentificaId id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
