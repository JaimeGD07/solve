package com.bad.solve.controller;

import com.bad.solve.dto.DetalleCatalogoRequest;
import com.bad.solve.entity.DetalleCatalogo;
import com.bad.solve.service.DetalleCatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-catalogo")
public class DetalleCatalogoController {
    private final DetalleCatalogoService service;

    public DetalleCatalogoController(DetalleCatalogoService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleCatalogo> listar(@RequestParam(required = false) Long codCat) {
        return codCat == null ? service.listar() : service.listarPorCatalogo(codCat);
    }

    @GetMapping("/{id}")
    public DetalleCatalogo obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public DetalleCatalogo crear(@RequestBody DetalleCatalogoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public DetalleCatalogo actualizar(@PathVariable Long id, @RequestBody DetalleCatalogoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
