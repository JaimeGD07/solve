package com.bad.solve.repository;

import com.bad.solve.entity.RespuestaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta, Long> {

    @Query(value = """
        SELECT NVL(MAX(INTENTO), 0)
        FROM RESPUESTA_ENCUESTA
        WHERE COD_USU = :codUsu
          AND COD_ENC = :codEnc
        """, nativeQuery = true)
    Integer obtenerUltimoIntento(
            @Param("codUsu") Long codUsu,
            @Param("codEnc") Long codEnc
    );

    @Query(value = """
        SELECT NVL(MAX(INTENTO), 0) + 1
        FROM RESPUESTA_ENCUESTA
        WHERE COD_USU = :codUsu
          AND COD_ENC = :codEnc
        """, nativeQuery = true)
    Integer obtenerSiguienteIntento(
            @Param("codUsu") Long codUsu,
            @Param("codEnc") Long codEnc
    );
}