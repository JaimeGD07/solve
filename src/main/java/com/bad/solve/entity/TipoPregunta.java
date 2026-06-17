package com.bad.solve.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_PREGUNTA")
public class TipoPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TIPO_PRE")
    private Long codTipoPre;

    @Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "ESCALA")
    private Integer escala;

    public Long getCodTipoPre() {
        return codTipoPre;
    }

    public void setCodTipoPre(Long codTipoPre) {
        this.codTipoPre = codTipoPre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEscala() {
        return escala;
    }

    public void setEscala(Integer escala) {
        this.escala = escala;
    }
}
