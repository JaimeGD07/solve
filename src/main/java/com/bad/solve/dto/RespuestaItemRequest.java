package com.bad.solve.dto;

public class RespuestaItemRequest {

    private Long codPre;
    private Long codDetCat;
    private Long codOpcResp;
    private String textoResp;
    private Integer valorResp;
    private Integer posicionRank;

    public Long getCodPre() {
        return codPre;
    }

    public void setCodPre(Long codPre) {
        this.codPre = codPre;
    }

    public Long getCodDetCat() {
        return codDetCat;
    }

    public void setCodDetCat(Long codDetCat) {
        this.codDetCat = codDetCat;
    }

    public Long getCodOpcResp() {
        return codOpcResp;
    }

    public void setCodOpcResp(Long codOpcResp) {
        this.codOpcResp = codOpcResp;
    }

    public String getTextoResp() {
        return textoResp;
    }

    public void setTextoResp(String textoResp) {
        this.textoResp = textoResp;
    }

    public Integer getValorResp() {
        return valorResp;
    }

    public void setValorResp(Integer valorResp) {
        this.valorResp = valorResp;
    }

    public Integer getPosicionRank() {
        return posicionRank;
    }

    public void setPosicionRank(Integer posicionRank) {
        this.posicionRank = posicionRank;
    }
}