package com.bad.solve.dto.auth;

import java.time.LocalDate;

public class RegistroRequest {
    private String primNom;
    private String segNom;
    private String primApell;
    private String segApell;
    private LocalDate fechNac;
    private String pais;
    private String ciudad;
    private String email;
    private String password;
    private Long codRol;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCodRol() {
        return codRol;
    }

    public void setCodRol(Long codRol) {
        this.codRol = codRol;
    }
}
