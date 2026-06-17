package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USU")
    private Long codUsu;

    @Column(name = "PRIM_NOM", length = 50, nullable = false)
    private String primNom;

    @Column(name = "SEG_NOM", length = 50)
    private String segNom;

    @Column(name = "PRIM_APELL", length = 50, nullable = false)
    private String primApell;

    @Column(name = "SEG_APELL", length = 50)
    private String segApell;

    @Column(name = "FECH_NAC", nullable = false)
    private LocalDate fechNac;

    @Column(name = "PAIS", length = 60)
    private String pais;

    @Column(name = "CIUDAD", length = 80)
    private String ciudad;

    @Column(name = "EMAIL", length = 254, nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASS_HASH", length = 255, nullable = false)
    private String passHash;

    @Column(name = "INTENTOS", nullable = false)
    private Integer intentos = 0;

    @Column(name = "ESTADO", length = 20, nullable = false)
    private String estado = "ACTIVO";

    public Long getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(Long codUsu) {
        this.codUsu = codUsu;
    }

    public String getPrimNom() {
        return primNom;
    }

    public void setPrimNom(String primNom) {
        this.primNom = primNom;
    }

    public String getSegNom() {
        return segNom;
    }

    public void setSegNom(String segNom) {
        this.segNom = segNom;
    }

    public String getPrimApell() {
        return primApell;
    }

    public void setPrimApell(String primApell) {
        this.primApell = primApell;
    }

    public String getSegApell() {
        return segApell;
    }

    public void setSegApell(String segApell) {
        this.segApell = segApell;
    }

    public LocalDate getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDate fechNac) {
        this.fechNac = fechNac;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
