package com.bad.solve.controller;

import com.bad.solve.dto.PreguntaRequest;
import com.bad.solve.entity.Pregunta;
import com.bad.solve.service.PreguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {
    private final PreguntaService service;

    public PreguntaController(PreguntaService service) {
        this.service = service;
    }

    /**
     * Lista preguntas con opción de filtrar por encuesta
     * 
     * @param codEnc - (Opcional) Código de la encuesta para filtrar
     *                 Si se omite, devuelve todas las preguntas
     * @return Lista de preguntas
     */
    @GetMapping
    public List<Pregunta> listar(@RequestParam(required = false) Long codEnc) {
        return codEnc == null ? service.listar() : service.listarPorEncuesta(codEnc);
    }

    /**
     * Obtiene una pregunta específica por su ID
     * 
     * @param id - Código de la pregunta a obtener
     * @return Pregunta solicitada
     */
    @GetMapping("/{id}")
    public Pregunta obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Crea una nueva pregunta
     * 
     * @param request - Objeto PreguntaRequest con los datos de la pregunta
     * @return Pregunta creada con ID asignado
     */
    @PostMapping
    public Pregunta crear(@RequestBody PreguntaRequest request) {
        return service.crear(request);
    }

    /**
     * Actualiza una pregunta existente
     * 
     * @param id - Código de la pregunta a actualizar
     * @param request - Objeto PreguntaRequest con los nuevos datos
     * @return Pregunta actualizada
     */
    @PutMapping("/{id}")
    public Pregunta actualizar(@PathVariable Long id, @RequestBody PreguntaRequest request) {
        return service.actualizar(id, request);
    }

    /**
     * Elimina una pregunta
     * 
     * @param id - Código de la pregunta a eliminar
     * @return Respuesta vacía (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
