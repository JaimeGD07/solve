package com.bad.solve.controller;

import com.bad.solve.dto.UsuarioProfesionRequest;
import com.bad.solve.entity.UsuarioProfesion;
import com.bad.solve.service.UsuarioProfesionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuario-profesiones")
public class UsuarioProfesionController {
    private final UsuarioProfesionService service;

    public UsuarioProfesionController(UsuarioProfesionService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioProfesion> listar(@RequestParam(required = false) Long codUsu) {
        return codUsu == null ? service.listar() : service.listarPorUsuario(codUsu);
    }

    @GetMapping("/{id}")
    public UsuarioProfesion obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public UsuarioProfesion crear(@RequestBody UsuarioProfesionRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public UsuarioProfesion actualizar(@PathVariable Long id, @RequestBody UsuarioProfesionRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
