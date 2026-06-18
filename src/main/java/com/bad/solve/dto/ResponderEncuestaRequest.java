package com.bad.solve.dto;

import java.util.List;

public class ResponderEncuestaRequest {

    private Long codUsu;
    private Long codEnc;
    private List<RespuestaItemRequest> respuestas;

    public Long getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(Long codUsu) {
        this.codUsu = codUsu;
    }

    public Long getCodEnc() {
        return codEnc;
    }

    public void setCodEnc(Long codEnc) {
        this.codEnc = codEnc;
    }

    public List<RespuestaItemRequest> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaItemRequest> respuestas) {
        this.respuestas = respuestas;
    }
}