package com.bad.solve.dto;

import java.util.List;

public class PreguntaCompletaRequest {

    private String texto;
    private String tipo;
    private Boolean obligatoria;
    private List<OpcionCompletaRequest> opciones;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public List<OpcionCompletaRequest> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionCompletaRequest> opciones) {
        this.opciones = opciones;
    }
}