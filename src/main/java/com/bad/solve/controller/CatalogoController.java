package com.bad.solve.controller;

import com.bad.solve.entity.Catalogo;
import com.bad.solve.service.CatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {
    private final CatalogoService service;

    public CatalogoController(CatalogoService service) {
        this.service = service;
    }

    /**
     * Lista todos los catálogos disponibles
     * 
     * @return Lista de todos los catálogos
     */
    @GetMapping
    public List<Catalogo> listar() {
        return service.listar();
    }

    /**
     * Obtiene un catálogo específico por su ID
     * 
     * @param id - Código del catálogo a obtener
     * @return Catalogo solicitado
     */
    @GetMapping("/{id}")
    public Catalogo obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Crea un nuevo catálogo
     * 
     * @param catalogo - Objeto con los datos del catálogo (incluir nombre)
     * @return Catalogo creado con ID asignado por la base de datos
     */
    @PostMapping
    public Catalogo crear(@RequestBody Catalogo catalogo) {
        return service.crear(catalogo);
    }

    /**
     * Actualiza un catálogo existente
     * 
     * @param id - Código del catálogo a actualizar
     * @param catalogo - Objeto con los nuevos datos
     * @return Catalogo actualizado
     */
    @PutMapping("/{id}")
    public Catalogo actualizar(@PathVariable Long id, @RequestBody Catalogo catalogo) {
        return service.actualizar(id, catalogo);
    }

    /**
     * Elimina un catálogo
     * 
     * @param id - Código del catálogo a eliminar
     * @return Respuesta vacía (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
