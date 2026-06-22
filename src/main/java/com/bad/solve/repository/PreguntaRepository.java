package com.bad.solve.repository;

import com.bad.solve.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    @Query(value = """
        SELECT *
        FROM PREGUNTA
        WHERE COD_ENC = :codEnc
        ORDER BY COD_PRE
        """, nativeQuery = true)
    List<Pregunta> findByEncuestaCodEnc(@Param("codEnc") Long codEnc);


    @Query(value = """
    SELECT 
        p.COD_PRE,
        p.PREGUNTA,
        p.OBLIGATORIA,
        p.COD_TIPO_PRE,
        tp.NOMBRE AS TIPO
    FROM PREGUNTA p
    INNER JOIN TIPO_PREGUNTA tp ON tp.COD_TIPO_PRE = p.COD_TIPO_PRE
    WHERE p.COD_ENC = :codEnc
    ORDER BY p.COD_PRE
    """, nativeQuery = true)
List<Object[]> listarPreguntasPorEncuesta(@Param("codEnc") Long codEnc);
}