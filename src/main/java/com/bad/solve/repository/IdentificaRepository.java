package com.bad.solve.repository;

import com.bad.solve.entity.Identifica;
import com.bad.solve.id.IdentificaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IdentificaRepository extends JpaRepository<Identifica, IdentificaId> {
    List<Identifica> findByUsuarioCodUsu(Long codUsu);
    List<Identifica> findByRolCodRol(Long codRol);
}
