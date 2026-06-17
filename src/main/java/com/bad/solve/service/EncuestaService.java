package com.bad.solve.service;

import com.bad.solve.entity.Encuesta;
import com.bad.solve.repository.EncuestaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EncuestaService {
    private final EncuestaRepository repository;

    public EncuestaService(EncuestaRepository repository) {
        this.repository = repository;
    }

    public List<Encuesta> listar() {
        return repository.findAll();
    }

    public Encuesta obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Encuesta no encontrada: " + id));
    }

    @Transactional
    public Encuesta crear(Encuesta encuesta) {
        return repository.save(encuesta);
    }

    @Transactional
    public Encuesta actualizar(Long id, Encuesta datos) {
        Encuesta encuesta = obtener(id);
        encuesta.setTitulo(datos.getTitulo());
        encuesta.setDescripcion(datos.getDescripcion());
        return repository.save(encuesta);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
