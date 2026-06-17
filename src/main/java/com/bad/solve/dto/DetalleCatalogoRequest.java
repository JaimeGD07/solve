package com.bad.solve.dto;

public class DetalleCatalogoRequest {
    private Long codCat;
    private String etiqueta;
    private Integer valor;
    private Integer orden;

    public Long getCodCat() {
        return codCat;
    }

    public void setCodCat(Long codCat) {
        this.codCat = codCat;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
