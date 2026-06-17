package com.bad.solve.service;

import com.bad.solve.entity.Rol;
import com.bad.solve.repository.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RolService {
    private final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    public List<Rol> listar() {
        return repository.findAll();
    }

    public Rol obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado: " + id));
    }

    @Transactional
    public Rol crear(Rol rol) {
        return repository.save(rol);
    }

    @Transactional
    public Rol actualizar(Long id, Rol datos) {
        Rol rol = obtener(id);
        rol.setNombre(datos.getNombre());
        rol.setDescripcion(datos.getDescripcion());
        return repository.save(rol);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
