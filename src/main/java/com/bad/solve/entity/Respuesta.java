package com.bad.solve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "RESPUESTA")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_RESP")
    private Long codResp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_RESP_ENC", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RespuestaEncuesta respuestaEncuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PRE", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pregunta pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_DET_CAT")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DetalleCatalogo detalleCatalogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_OPC_RESP")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OpcionRespuesta opcionRespuesta;

    @Lob
    @Column(name = "TEXTO_RESP")
    private String textoResp;

    @Column(name = "VALOR_RESP")
    private Integer valorResp;

    @Column(name = "POSICION_RANK")
    private Integer posicionRank;

    public Long getCodResp() { return codResp; }
    public void setCodResp(Long codResp) { this.codResp = codResp; }
    public RespuestaEncuesta getRespuestaEncuesta() { return respuestaEncuesta; }
    public void setRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta) { this.respuestaEncuesta = respuestaEncuesta; }
    public Pregunta getPregunta() { return pregunta; }
    public void setPregunta(Pregunta pregunta) { this.pregunta = pregunta; }
    public DetalleCatalogo getDetalleCatalogo() { return detalleCatalogo; }
    public void setDetalleCatalogo(DetalleCatalogo detalleCatalogo) { this.detalleCatalogo = detalleCatalogo; }
    public OpcionRespuesta getOpcionRespuesta() { return opcionRespuesta; }
    public void setOpcionRespuesta(OpcionRespuesta opcionRespuesta) { this.opcionRespuesta = opcionRespuesta; }
    public String getTextoResp() { return textoResp; }
    public void setTextoResp(String textoResp) { this.textoResp = textoResp; }
    public Integer getValorResp() { return valorResp; }
    public void setValorResp(Integer valorResp) { this.valorResp = valorResp; }
    public Integer getPosicionRank() { return posicionRank; }
    public void setPosicionRank(Integer posicionRank) { this.posicionRank = posicionRank; }
}
