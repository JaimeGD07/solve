package com.bad.solve.service;

import com.bad.solve.dto.UsuarioProfesionRequest;
import com.bad.solve.entity.Usuario;
import com.bad.solve.entity.UsuarioProfesion;
import com.bad.solve.repository.UsuarioProfesionRepository;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioProfesionService {
    private final UsuarioProfesionRepository repository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioProfesionService(UsuarioProfesionRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioProfesion> listar() { return repository.findAll(); }
    public List<UsuarioProfesion> listarPorUsuario(Long codUsu) { return repository.findByUsuarioCodUsu(codUsu); }
    public UsuarioProfesion obtener(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profesión no encontrada: " + id)); }

    @Transactional
    public UsuarioProfesion crear(UsuarioProfesionRequest request) {
        UsuarioProfesion usuarioProfesion = new UsuarioProfesion();
        aplicarDatos(usuarioProfesion, request);
        return repository.save(usuarioProfesion);
    }

    @Transactional
    public UsuarioProfesion actualizar(Long id, UsuarioProfesionRequest request) {
        UsuarioProfesion usuarioProfesion = obtener(id);
        aplicarDatos(usuarioProfesion, request);
        return repository.save(usuarioProfesion);
    }

    @Transactional public void eliminar(Long id) { repository.delete(obtener(id)); }

    private void aplicarDatos(UsuarioProfesion usuarioProfesion, UsuarioProfesionRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + request.getCodUsu()));
        usuarioProfesion.setUsuario(usuario);
        usuarioProfesion.setProfesion(request.getProfesion());
    }
}
