package com.bad.solve.controller;

import com.bad.solve.dto.RespuestaRequest;
import com.bad.solve.entity.Respuesta;
import com.bad.solve.service.RespuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {
    private final RespuestaService service;

    public RespuestaController(RespuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Respuesta> listar(@RequestParam(required = false) Long codRespEnc) {
        return codRespEnc == null ? service.listar() : service.listarPorRespuestaEncuesta(codRespEnc);
    }

    @GetMapping("/{id}")
    public Respuesta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Respuesta crear(@RequestBody RespuestaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public Respuesta actualizar(@PathVariable Long id, @RequestBody RespuestaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
