package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESPUESTA_ENCUESTA")
public class RespuestaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_RESP_ENC")
    private Long codRespEnc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_ENC", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Encuesta encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USU", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    @Column(name = "FECH_INICIO", nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechInicio;

    @Column(name = "FECH_FIN")
    private LocalDateTime fechFin;

    @Column(name = "ESTADO", length = 20, nullable = false)
    private String estado = "EN_PROCESO";

    @Column(name = "INTENTO", nullable = false)
    private Integer intento = 1;

    public Long getCodRespEnc() {
        return codRespEnc;
    }

    public void setCodRespEnc(Long codRespEnc) {
        this.codRespEnc = codRespEnc;
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

    public LocalDateTime getFechInicio() {
        return fechInicio;
    }

    public void setFechInicio(LocalDateTime fechInicio) {
        this.fechInicio = fechInicio;
    }

    public LocalDateTime getFechFin() {
        return fechFin;
    }

    public void setFechFin(LocalDateTime fechFin) {
        this.fechFin = fechFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIntento() {
        return intento;
    }

    public void setIntento(Integer intento) {
        this.intento = intento;
    }
}
