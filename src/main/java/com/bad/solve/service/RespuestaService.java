package com.bad.solve.service;

import com.bad.solve.dto.RespuestaRequest;
import com.bad.solve.entity.*;
import com.bad.solve.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RespuestaService {
    private final RespuestaRepository repository;
    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final DetalleCatalogoRepository detalleCatalogoRepository;
    private final OpcionRespuestaRepository opcionRespuestaRepository;

    public RespuestaService(RespuestaRepository repository,
            RespuestaEncuestaRepository respuestaEncuestaRepository,
            PreguntaRepository preguntaRepository,
            DetalleCatalogoRepository detalleCatalogoRepository,
            OpcionRespuestaRepository opcionRespuestaRepository) {
        this.repository = repository;
        this.respuestaEncuestaRepository = respuestaEncuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.detalleCatalogoRepository = detalleCatalogoRepository;
        this.opcionRespuestaRepository = opcionRespuestaRepository;
    }

    public List<Respuesta> listar() {
        return repository.findAll();
    }

    public List<Respuesta> listarPorRespuestaEncuesta(Long codRespEnc) {
        return repository.findByRespuestaEncuestaCodRespEnc(codRespEnc);
    }

    public Respuesta obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada: " + id));
    }

    @Transactional
    public Respuesta crear(RespuestaRequest request) {
        Respuesta respuesta = new Respuesta();
        aplicarDatos(respuesta, request);
        return repository.save(respuesta);
    }

    @Transactional
    public Respuesta actualizar(Long id, RespuestaRequest request) {
        Respuesta respuesta = obtener(id);
        aplicarDatos(respuesta, request);
        return repository.save(respuesta);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void aplicarDatos(Respuesta respuesta, RespuestaRequest request) {
        RespuestaEncuesta respuestaEncuesta = respuestaEncuestaRepository.findById(request.getCodRespEnc())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Respuesta encuesta no encontrada: " + request.getCodRespEnc()));
        Pregunta pregunta = preguntaRepository.findById(request.getCodPre())
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada: " + request.getCodPre()));

        respuesta.setRespuestaEncuesta(respuestaEncuesta);
        respuesta.setPregunta(pregunta);
        respuesta.setTextoResp(request.getTextoResp());
        respuesta.setValorResp(request.getValorResp());
        respuesta.setPosicionRank(request.getPosicionRank());

        if (request.getCodDetCat() != null) {
            DetalleCatalogo detalle = detalleCatalogoRepository.findById(request.getCodDetCat())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Detalle catálogo no encontrado: " + request.getCodDetCat()));
            respuesta.setDetalleCatalogo(detalle);
        } else {
            respuesta.setDetalleCatalogo(null);
        }

        if (request.getCodOpcResp() != null) {
            OpcionRespuesta opcion = opcionRespuestaRepository.findById(request.getCodOpcResp())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Opción respuesta no encontrada: " + request.getCodOpcResp()));
            respuesta.setOpcionRespuesta(opcion);
        } else {
            respuesta.setOpcionRespuesta(null);
        }
    }
}
