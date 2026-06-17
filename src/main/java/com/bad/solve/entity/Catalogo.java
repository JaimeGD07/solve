package com.bad.solve.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CATALOGO")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CAT")
    private Long codCat;

    /**
     * Nombre único del catálogo
     * Máximo 50 caracteres
     * Ejemplos: "GENERO", "ESTADO_CIVIL", "NIVEL_EDUCACION"
     */
    @Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
    private String nombre;

    public Long getCodCat() {
        return codCat;
    }

    public void setCodCat(Long codCat) {
        this.codCat = codCat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
