package com.bad.solve.controller;

import com.bad.solve.dto.OpcionRespuestaRequest;
import com.bad.solve.entity.OpcionRespuesta;
import com.bad.solve.service.OpcionRespuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/opciones-respuesta")
public class OpcionRespuestaController {
    private final OpcionRespuestaService service;

    public OpcionRespuestaController(OpcionRespuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<OpcionRespuesta> listar(@RequestParam(required = false) Long codPre) {
        return codPre == null ? service.listar() : service.listarPorPregunta(codPre);
    }

    @GetMapping("/{id}")
    public OpcionRespuesta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public OpcionRespuesta crear(@RequestBody OpcionRespuestaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public OpcionRespuesta actualizar(@PathVariable Long id, @RequestBody OpcionRespuestaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
