package com.bad.solve.controller;

import com.bad.solve.dto.AsignacionEncuestaRequest;
import com.bad.solve.entity.UsuarioEncuesta;
import com.bad.solve.service.UsuarioEncuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones-encuesta")
public class UsuarioEncuestaController {
    private final UsuarioEncuestaService service;

    public UsuarioEncuestaController(UsuarioEncuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioEncuesta> listar(@RequestParam(required = false) Long codUsu,
            @RequestParam(required = false) Long codEnc) {
        if (codUsu != null)
            return service.listarPorUsuario(codUsu);
        if (codEnc != null)
            return service.listarPorEncuesta(codEnc);
        return service.listar();
    }

    @PostMapping
    public UsuarioEncuesta asignar(@RequestBody AsignacionEncuestaRequest request) {
        return service.asignar(request);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam Long codEnc, @RequestParam Long codUsu) {
        service.eliminar(codEnc, codUsu);
        return ResponseEntity.noContent().build();
    }
}
