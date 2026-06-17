package com.bad.solve.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IdentificaId implements Serializable {

    @Column(name = "COD_ROL")
    private Long codRol;

    @Column(name = "COD_USU")
    private Long codUsu;

    public IdentificaId() {}

    public IdentificaId(Long codRol, Long codUsu) {
        this.codRol = codRol;
        this.codUsu = codUsu;
    }

    public Long getCodRol() { return codRol; }
    public void setCodRol(Long codRol) { this.codRol = codRol; }
    public Long getCodUsu() { return codUsu; }
    public void setCodUsu(Long codUsu) { this.codUsu = codUsu; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentificaId that)) return false;
        return Objects.equals(codRol, that.codRol) && Objects.equals(codUsu, that.codUsu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codRol, codUsu);
    }
}
