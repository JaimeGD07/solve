package com.bad.solve.service;

import com.bad.solve.entity.Catalogo;
import com.bad.solve.repository.CatalogoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CatalogoService {
    private final CatalogoRepository repository;

    public CatalogoService(CatalogoRepository repository) {
        this.repository = repository;
    }

    public List<Catalogo> listar() {
        return repository.findAll();
    }

    public Catalogo obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catálogo no encontrado: " + id));
    }

    @Transactional
    public Catalogo crear(Catalogo catalogo) {
        return repository.save(catalogo);
    }

    @Transactional
    public Catalogo actualizar(Long id, Catalogo datos) {
        Catalogo catalogo = obtener(id);
        catalogo.setNombre(datos.getNombre());
        return repository.save(catalogo);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
