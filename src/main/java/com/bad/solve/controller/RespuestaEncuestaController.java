package com.bad.solve.controller;

import com.bad.solve.dto.RespuestaEncuestaRequest;
import com.bad.solve.entity.RespuestaEncuesta;
import com.bad.solve.service.RespuestaEncuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/respuestas-encuesta")
public class RespuestaEncuestaController {
    private final RespuestaEncuestaService service;

    public RespuestaEncuestaController(RespuestaEncuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<RespuestaEncuesta> listar(@RequestParam(required = false) Long codUsu,
            @RequestParam(required = false) Long codEnc) {
        if (codUsu != null)
            return service.listarPorUsuario(codUsu);
        if (codEnc != null)
            return service.listarPorEncuesta(codEnc);
        return service.listar();
    }

    @GetMapping("/{id}")
    public RespuestaEncuesta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/iniciar")
    public RespuestaEncuesta iniciar(@RequestBody RespuestaEncuestaRequest request) {
        return service.iniciar(request);
    }

    @PutMapping("/{id}/finalizar")
    public RespuestaEncuesta finalizar(@PathVariable Long id) {
        return service.finalizar(id);
    }

    @PutMapping("/{id}/anular")
    public RespuestaEncuesta anular(@PathVariable Long id) {
        return service.anular(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
