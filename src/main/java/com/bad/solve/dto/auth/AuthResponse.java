package com.bad.solve.dto.auth;

import java.util.List;

public class AuthResponse {

    private String token;
    private String tipoToken;
    private Long codUsu;
    private String nombreCompleto;
    private String email;
    private String estado;
    private List<String> roles;

    public AuthResponse() {
    }

    public AuthResponse(
            String token,
            String tipoToken,
            Long codUsu,
            String nombreCompleto,
            String email,
            String estado,
            List<String> roles
    ) {
        this.token = token;
        this.tipoToken = tipoToken;
        this.codUsu = codUsu;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.estado = estado;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public Long getCodUsu() {
        return codUsu;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public void setCodUsu(Long codUsu) {
        this.codUsu = codUsu;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}