package com.bad.solve.service;

import com.bad.solve.entity.TipoPregunta;
import com.bad.solve.repository.TipoPreguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TipoPreguntaService {
    private final TipoPreguntaRepository repository;

    public TipoPreguntaService(TipoPreguntaRepository repository) {
        this.repository = repository;
    }

    public List<TipoPregunta> listar() {
        return repository.findAll();
    }

    public TipoPregunta obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de pregunta no encontrado: " + id));
    }

    @Transactional
    public TipoPregunta crear(TipoPregunta tipo) {
        return repository.save(tipo);
    }

    @Transactional
    public TipoPregunta actualizar(Long id, TipoPregunta datos) {
        TipoPregunta tipo = obtener(id);
        tipo.setNombre(datos.getNombre());
        tipo.setEscala(datos.getEscala());
        return repository.save(tipo);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
