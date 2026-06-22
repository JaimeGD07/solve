package com.bad.solve.repository;

import com.bad.solve.entity.OpcionRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpcionRespuestaRepository extends JpaRepository<OpcionRespuesta, Long> {

    @Query(value = """
        SELECT *
        FROM OPCION_RESPUESTA
        WHERE COD_PRE = :codPre
        ORDER BY ORDEN
        """, nativeQuery = true)
    List<OpcionRespuesta> findByPreguntaCodPreOrderByOrdenAsc(@Param("codPre") Long codPre);


    @Query(value = """
        SELECT 
            COD_OPC_RESP,
            OPCION,
            VALOR,
            ORDEN
        FROM OPCION_RESPUESTA
        WHERE COD_PRE = :codPre
        ORDER BY ORDEN
        """, nativeQuery = true)
    List<Object[]> listarOpcionesPorPregunta(@Param("codPre") Long codPre);
}