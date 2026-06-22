package com.bad.solve.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "http://localhost:4200")
public class PreguntaController {

    private final JdbcTemplate jdbcTemplate;

    public PreguntaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/encuesta/{codEnc}")
    public ResponseEntity<?> listarPorEncuesta(@PathVariable Long codEnc) {
        try {
            String sql = """
                        SELECT
                            p.COD_PRE,
                            p.COD_ENC,
                            p.PREGUNTA,
                            p.OBLIGATORIA,
                            p.COD_TIPO_PRE,
                            tp.NOMBRE AS TIPO
                        FROM PREGUNTA p
                        LEFT JOIN TIPO_PREGUNTA tp ON tp.COD_TIPO_PRE = p.COD_TIPO_PRE
                        WHERE p.COD_ENC = ?
                        ORDER BY p.COD_PRE
                    """;

            List<Map<String, Object>> preguntas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                Map<String, Object> item = new LinkedHashMap<>();

                item.put("codPre", rs.getLong("COD_PRE"));
                item.put("codEnc", rs.getLong("COD_ENC"));
                item.put("enunciado", rs.getString("PREGUNTA"));
                item.put("pregunta", rs.getString("PREGUNTA"));
                item.put("texto", rs.getString("PREGUNTA"));

                Object obligatoria = rs.getObject("OBLIGATORIA");
                item.put("obligatoria", obligatoria);

                item.put("codTipoPre", rs.getLong("COD_TIPO_PRE"));
                item.put("tipo", rs.getString("TIPO"));
                item.put("opciones", new ArrayList<>());

                return item;
            }, codEnc);

            return ResponseEntity.ok(preguntas);

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new LinkedHashMap<>();
            error.put("mensaje", e.getMessage());
            error.put("endpoint", "/api/preguntas/encuesta/" + codEnc);

            return ResponseEntity.status(500).body(error);
        }
    }
    
}