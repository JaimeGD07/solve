package com.bad.solve.dto;

import java.util.List;

public class CrearEncuestaCompletaRequest {

    private String titulo;
    private String descripcion;
    private List<PreguntaCompletaRequest> preguntas;

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

    public List<PreguntaCompletaRequest> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaCompletaRequest> preguntas) {
        this.preguntas = preguntas;
    }
}
