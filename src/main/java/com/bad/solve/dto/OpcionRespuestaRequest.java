package com.bad.solve.dto;

public class OpcionRespuestaRequest {
    private Long codPre;
    private String opcion;
    private Integer valor;
    private Integer valorMax;
    private Integer valorMin;
    private Integer orden;

    public Long getCodPre() {
        return codPre;
    }

    public void setCodPre(Long codPre) {
        this.codPre = codPre;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorMax() {
        return valorMax;
    }

    public void setValorMax(Integer valorMax) {
        this.valorMax = valorMax;
    }

    public Integer getValorMin() {
        return valorMin;
    }

    public void setValorMin(Integer valorMin) {
        this.valorMin = valorMin;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
