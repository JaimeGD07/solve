package com.bad.solve.repository;

import com.bad.solve.entity.UsuarioEncuesta;
import com.bad.solve.id.UsuarioEncuestaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioEncuestaRepository extends JpaRepository<UsuarioEncuesta, UsuarioEncuestaId> {
    List<UsuarioEncuesta> findByUsuarioCodUsu(Long codUsu);

    List<UsuarioEncuesta> findByEncuestaCodEnc(Long codEnc);

    boolean existsByEncuestaCodEncAndUsuarioCodUsu(Long codEnc, Long codUsu);
}
