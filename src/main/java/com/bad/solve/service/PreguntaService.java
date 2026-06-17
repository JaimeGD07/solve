package com.bad.solve.service;

import com.bad.solve.dto.PreguntaRequest;
import com.bad.solve.entity.Catalogo;
import com.bad.solve.entity.Encuesta;
import com.bad.solve.entity.Pregunta;
import com.bad.solve.entity.TipoPregunta;
import com.bad.solve.repository.CatalogoRepository;
import com.bad.solve.repository.EncuestaRepository;
import com.bad.solve.repository.PreguntaRepository;
import com.bad.solve.repository.TipoPreguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PreguntaService {
    private final PreguntaRepository repository;
    private final EncuestaRepository encuestaRepository;
    private final TipoPreguntaRepository tipoPreguntaRepository;
    private final CatalogoRepository catalogoRepository;

    public PreguntaService(PreguntaRepository repository, EncuestaRepository encuestaRepository,
            TipoPreguntaRepository tipoPreguntaRepository, CatalogoRepository catalogoRepository) {
        this.repository = repository;
        this.encuestaRepository = encuestaRepository;
        this.tipoPreguntaRepository = tipoPreguntaRepository;
        this.catalogoRepository = catalogoRepository;
    }

    public List<Pregunta> listar() {
        return repository.findAll();
    }

    public List<Pregunta> listarPorEncuesta(Long codEnc) {
        return repository.findByEncuestaCodEnc(codEnc);
    }

    public Pregunta obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada: " + id));
    }

    @Transactional
    public Pregunta crear(PreguntaRequest request) {
        Pregunta pregunta = new Pregunta();
        aplicarDatos(pregunta, request);
        return repository.save(pregunta);
    }

    @Transactional
    public Pregunta actualizar(Long id, PreguntaRequest request) {
        Pregunta pregunta = obtener(id);
        aplicarDatos(pregunta, request);
        return repository.save(pregunta);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void aplicarDatos(Pregunta pregunta, PreguntaRequest request) {
        Encuesta encuesta = encuestaRepository.findById(request.getCodEnc())
                .orElseThrow(() -> new EntityNotFoundException("Encuesta no encontrada: " + request.getCodEnc()));
        TipoPregunta tipo = tipoPreguntaRepository.findById(request.getCodTipoPre())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de pregunta no encontrado: " + request.getCodTipoPre()));
        Catalogo catalogo = catalogoRepository.findById(request.getCodCat())
                .orElseThrow(() -> new EntityNotFoundException("Catálogo no encontrado: " + request.getCodCat()));
        pregunta.setEncuesta(encuesta);
        pregunta.setTipoPregunta(tipo);
        pregunta.setCatalogo(catalogo);
        pregunta.setEnunciado(request.getEnunciado());
        pregunta.setObligatoria(request.getObligatoria() == null ? 1 : request.getObligatoria());
    }
}
