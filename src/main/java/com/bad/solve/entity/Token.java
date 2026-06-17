package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TOKEN")
    private Long codToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USU", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    @Column(name = "TOKEN", length = 255, nullable = false, unique = true)
    private String token;

    @Column(name = "FECH_CREA", nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechCrea;

    @Column(name = "FECH_EXP", nullable = false)
    private LocalDateTime fechExp;

    @Column(name = "UTILIZADO", nullable = false)
    private Integer utilizado = 0;

    public Long getCodToken() {
        return codToken;
    }

    public void setCodToken(Long codToken) {
        this.codToken = codToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechCrea() {
        return fechCrea;
    }

    public void setFechCrea(LocalDateTime fechCrea) {
        this.fechCrea = fechCrea;
    }

    public LocalDateTime getFechExp() {
        return fechExp;
    }

    public void setFechExp(LocalDateTime fechExp) {
        this.fechExp = fechExp;
    }

    public Integer getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Integer utilizado) {
        this.utilizado = utilizado;
    }
}
