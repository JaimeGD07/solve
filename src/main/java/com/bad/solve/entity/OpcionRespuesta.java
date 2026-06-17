package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "OPCION_RESPUESTA")
public class OpcionRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_OPC_RESP")
    private Long codOpcResp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PRE", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Pregunta pregunta;

    @Column(name = "OPCION", length = 200, nullable = false)
    private String opcion;

    @Column(name = "VALOR")
    private Integer valor;

    @Column(name = "VALOR_MAX")
    private Integer valorMax;

    @Column(name = "VALOR_MIN")
    private Integer valorMin;

    @Column(name = "ORDEN")
    private Integer orden;

    public Long getCodOpcResp() {
        return codOpcResp;
    }

    public void setCodOpcResp(Long codOpcResp) {
        this.codOpcResp = codOpcResp;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
