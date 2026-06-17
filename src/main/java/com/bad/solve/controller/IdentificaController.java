package com.bad.solve.controller;

import com.bad.solve.dto.AsignacionRolRequest;
import com.bad.solve.entity.Identifica;
import com.bad.solve.service.IdentificaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuario-roles")
public class IdentificaController {
    private final IdentificaService service;

    public IdentificaController(IdentificaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Identifica> listar(@RequestParam(required = false) Long codUsu) {
        return codUsu == null ? service.listar() : service.listarPorUsuario(codUsu);
    }

    @PostMapping
    public Identifica asignar(@RequestBody AsignacionRolRequest request) {
        return service.asignar(request);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam Long codRol, @RequestParam Long codUsu) {
        service.eliminar(codRol, codUsu);
        return ResponseEntity.noContent().build();
    }
}
