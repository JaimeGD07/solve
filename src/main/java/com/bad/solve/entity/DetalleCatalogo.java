package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_CATALOGO")
public class DetalleCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DET_CAT")
    private Long codDetCat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_CAT", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Catalogo catalogo;

    @Column(name = "ETIQUETA", length = 100, nullable = false)
    private String etiqueta;

    @Column(name = "VALOR")
    private Integer valor;

    @Column(name = "ORDEN")
    private Integer orden;

    public Long getCodDetCat() {
        return codDetCat;
    }

    public void setCodDetCat(Long codDetCat) {
        this.codDetCat = codDetCat;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
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
