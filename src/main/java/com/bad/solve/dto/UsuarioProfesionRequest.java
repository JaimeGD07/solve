package com.bad.solve.dto;

public class UsuarioProfesionRequest {
    private Long codUsu;
    private String profesion;

    public Long getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(Long codUsu) {
        this.codUsu = codUsu;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
