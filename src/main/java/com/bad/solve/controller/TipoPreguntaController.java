package com.bad.solve.controller;

import com.bad.solve.entity.TipoPregunta;
import com.bad.solve.service.TipoPreguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-pregunta")
public class TipoPreguntaController {
    private final TipoPreguntaService service;

    public TipoPreguntaController(TipoPreguntaService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoPregunta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoPregunta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public TipoPregunta crear(@RequestBody TipoPregunta tipo) {
        return service.crear(tipo);
    }

    @PutMapping("/{id}")
    public TipoPregunta actualizar(@PathVariable Long id, @RequestBody TipoPregunta tipo) {
        return service.actualizar(id, tipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
