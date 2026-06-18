package com.bad.solve.entity;

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
    private Usuario usuario;

    @Column(name = "TOKEN", nullable = false, length = 255)
    private String token;

    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;

    @Column(name = "FECH_CREACION", nullable = false)
    private LocalDateTime fechCreacion;

    @Column(name = "FECH_EXPIRACION", nullable = false)
    private LocalDateTime fechExpiracion;

    @Column(name = "UTILIZADO", nullable = false)
    private Integer utilizado;

    public Token() {
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechCreacion() {
        return fechCreacion;
    }

    public void setFechCreacion(LocalDateTime fechCreacion) {
        this.fechCreacion = fechCreacion;
    }

    public LocalDateTime getFechExpiracion() {
        return fechExpiracion;
    }

    public void setFechExpiracion(LocalDateTime fechExpiracion) {
        this.fechExpiracion = fechExpiracion;
    }

    public Integer getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Integer utilizado) {
        this.utilizado = utilizado;
    }
}