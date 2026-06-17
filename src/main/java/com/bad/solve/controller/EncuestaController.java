package com.bad.solve.controller;

import com.bad.solve.entity.Encuesta;
import com.bad.solve.service.EncuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {
    private final EncuestaService service;

    public EncuestaController(EncuestaService service) {
        this.service = service;
    }

    /**
     * Lista todas las encuestas disponibles
     * 
     * @return Lista de todas las encuestas
     */
    @GetMapping
    public List<Encuesta> listar() {
        return service.listar();
    }

    /**
     * Obtiene una encuesta específica por su ID
     * 
     * @param id - Código de la encuesta a obtener
     * @return Encuesta solicitada
     */
    @GetMapping("/{id}")
    public Encuesta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Crea una nueva encuesta
     * 
     * @param encuesta - Objeto con los datos de la encuesta
     * @return Encuesta creada con ID asignado
     */
    @PostMapping
    public Encuesta crear(@RequestBody Encuesta encuesta) {
        return service.crear(encuesta);
    }

    /**
     * Actualiza una encuesta existente
     * 
     * @param id - Código de la encuesta a actualizar
     * @param encuesta - Objeto con los nuevos datos
     * @return Encuesta actualizada
     */
    @PutMapping("/{id}")
    public Encuesta actualizar(@PathVariable Long id, @RequestBody Encuesta encuesta) {
        return service.actualizar(id, encuesta);
    }

    /**
     * Elimina una encuesta
     * 
     * @param id - Código de la encuesta a eliminar
     * @return Respuesta vacía (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
