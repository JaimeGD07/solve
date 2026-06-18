package com.bad.solve.controller;

import com.bad.solve.dto.ResponderEncuestaRequest;
import com.bad.solve.entity.RespuestaEncuesta;
import com.bad.solve.service.RespuestaEncuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/respuestas-encuesta")
@CrossOrigin(origins = "http://localhost:4200")
public class RespuestaEncuestaController {

    private final RespuestaEncuestaService respuestaEncuestaService;

    public RespuestaEncuestaController(RespuestaEncuestaService respuestaEncuestaService) {
        this.respuestaEncuestaService = respuestaEncuestaService;
    }

    @GetMapping
    public ResponseEntity<List<RespuestaEncuesta>> listar() {
        return ResponseEntity.ok(respuestaEncuestaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEncuesta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(respuestaEncuestaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RespuestaEncuesta> guardar(@RequestBody RespuestaEncuesta respuestaEncuesta) {
        return ResponseEntity.ok(respuestaEncuestaService.guardar(respuestaEncuesta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEncuesta> actualizar(
            @PathVariable Long id,
            @RequestBody RespuestaEncuesta respuestaEncuesta
    ) {
        return ResponseEntity.ok(respuestaEncuestaService.actualizar(id, respuestaEncuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        respuestaEncuestaService.eliminar(id);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Respuesta de encuesta eliminada correctamente"
        ));
    }

    @PostMapping("/responder")
    public ResponseEntity<Map<String, Object>> responderEncuesta(
            @RequestBody ResponderEncuestaRequest request
    ) {
        Long codRespEnc = respuestaEncuestaService.responderEncuesta(request);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Encuesta respondida correctamente",
                "codRespEnc", codRespEnc
        ));
    }
}