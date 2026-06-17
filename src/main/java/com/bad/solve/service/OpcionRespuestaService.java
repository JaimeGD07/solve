package com.bad.solve.service;

import com.bad.solve.dto.OpcionRespuestaRequest;
import com.bad.solve.entity.OpcionRespuesta;
import com.bad.solve.entity.Pregunta;
import com.bad.solve.repository.OpcionRespuestaRepository;
import com.bad.solve.repository.PreguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OpcionRespuestaService {
    private final OpcionRespuestaRepository repository;
    private final PreguntaRepository preguntaRepository;

    public OpcionRespuestaService(OpcionRespuestaRepository repository, PreguntaRepository preguntaRepository) {
        this.repository = repository;
        this.preguntaRepository = preguntaRepository;
    }

    public List<OpcionRespuesta> listar() {
        return repository.findAll();
    }

    public List<OpcionRespuesta> listarPorPregunta(Long codPre) {
        return repository.findByPreguntaCodPreOrderByOrdenAsc(codPre);
    }

    public OpcionRespuesta obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Opción no encontrada: " + id));
    }

    @Transactional
    public OpcionRespuesta crear(OpcionRespuestaRequest request) {
        OpcionRespuesta opcion = new OpcionRespuesta();
        aplicarDatos(opcion, request);
        return repository.save(opcion);
    }

    @Transactional
    public OpcionRespuesta actualizar(Long id, OpcionRespuestaRequest request) {
        OpcionRespuesta opcion = obtener(id);
        aplicarDatos(opcion, request);
        return repository.save(opcion);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void aplicarDatos(OpcionRespuesta opcion, OpcionRespuestaRequest request) {
        Pregunta pregunta = preguntaRepository.findById(request.getCodPre())
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada: " + request.getCodPre()));
        opcion.setPregunta(pregunta);
        opcion.setOpcion(request.getOpcion());
        opcion.setValor(request.getValor());
        opcion.setValorMax(request.getValorMax());
        opcion.setValorMin(request.getValorMin());
        opcion.setOrden(request.getOrden());
    }
}
