package com.bad.solve.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENCUESTA")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ENC")
    private Long codEnc;

    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "NUM_PREGUNTAS", nullable = false, insertable = false, updatable = false)
    private Integer numPreguntas;

    @Column(name = "FECH_CREA", nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechCrea;

    public Long getCodEnc() {
        return codEnc;
    }

    public void setCodEnc(Long codEnc) {
        this.codEnc = codEnc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(Integer numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

    public LocalDateTime getFechCrea() {
        return fechCrea;
    }

    public void setFechCrea(LocalDateTime fechCrea) {
        this.fechCrea = fechCrea;
    }
}
