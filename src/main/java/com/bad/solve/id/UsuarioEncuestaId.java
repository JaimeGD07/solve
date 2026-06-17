package com.bad.solve.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioEncuestaId implements Serializable {

    @Column(name = "COD_ENC")
    private Long codEnc;

    @Column(name = "COD_USU")
    private Long codUsu;

    public UsuarioEncuestaId() {}

    public UsuarioEncuestaId(Long codEnc, Long codUsu) {
        this.codEnc = codEnc;
        this.codUsu = codUsu;
    }

    public Long getCodEnc() { return codEnc; }
    public void setCodEnc(Long codEnc) { this.codEnc = codEnc; }
    public Long getCodUsu() { return codUsu; }
    public void setCodUsu(Long codUsu) { this.codUsu = codUsu; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioEncuestaId that)) return false;
        return Objects.equals(codEnc, that.codEnc) && Objects.equals(codUsu, that.codUsu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEnc, codUsu);
    }
}
