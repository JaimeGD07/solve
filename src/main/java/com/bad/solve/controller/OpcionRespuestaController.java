package com.bad.solve.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/opciones-respuesta")
@CrossOrigin(origins = "http://localhost:4200")
public class OpcionRespuestaController {

    private final JdbcTemplate jdbcTemplate;

    public OpcionRespuestaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/pregunta/{codPre}")
    public ResponseEntity<List<Map<String, Object>>> listarPorPregunta(@PathVariable Long codPre) {

        String sql = """
            SELECT
                COD_OPC_RESP,
                OPCION,
                VALOR,
                ORDEN
            FROM OPCION_RESPUESTA
            WHERE COD_PRE = ?
            ORDER BY ORDEN
        """;

        List<Map<String, Object>> opciones = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> item = new LinkedHashMap<>();

            item.put("codOpcResp", rs.getLong("COD_OPC_RESP"));
            item.put("codOpc", rs.getLong("COD_OPC_RESP"));
            item.put("opcion", rs.getString("OPCION"));
            item.put("texto", rs.getString("OPCION"));
            item.put("valor", rs.getInt("VALOR"));
            item.put("orden", rs.getInt("ORDEN"));

            return item;
        }, codPre);

        return ResponseEntity.ok(opciones);
    }
}