package com.bad.solve.repository;

import com.bad.solve.entity.RespuestaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta, Long> {
    List<RespuestaEncuesta> findByUsuarioCodUsu(Long codUsu);

    List<RespuestaEncuesta> findByEncuestaCodEnc(Long codEnc);

    @Query("select coalesce(max(r.intento), 0) from RespuestaEncuesta r where r.encuesta.codEnc = :codEnc and r.usuario.codUsu = :codUsu")
    Integer obtenerUltimoIntento(@Param("codEnc") Long codEnc, @Param("codUsu") Long codUsu);
}
