package com.bad.solve.service;

import com.bad.solve.dto.AsignacionEncuestaRequest;
import com.bad.solve.entity.Encuesta;
import com.bad.solve.entity.Usuario;
import com.bad.solve.entity.UsuarioEncuesta;
import com.bad.solve.id.UsuarioEncuestaId;
import com.bad.solve.repository.EncuestaRepository;
import com.bad.solve.repository.UsuarioEncuestaRepository;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioEncuestaService {
    private final UsuarioEncuestaRepository repository;
    private final EncuestaRepository encuestaRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioEncuestaService(UsuarioEncuestaRepository repository, EncuestaRepository encuestaRepository,
            UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.encuestaRepository = encuestaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioEncuesta> listar() {
        return repository.findAll();
    }

    public List<UsuarioEncuesta> listarPorUsuario(Long codUsu) {
        return repository.findByUsuarioCodUsu(codUsu);
    }

    public List<UsuarioEncuesta> listarPorEncuesta(Long codEnc) {
        return repository.findByEncuestaCodEnc(codEnc);
    }

    @Transactional
    public UsuarioEncuesta asignar(AsignacionEncuestaRequest request) {
        Encuesta encuesta = encuestaRepository.findById(request.getCodEnc())
                .orElseThrow(() -> new EntityNotFoundException("Encuesta no encontrada: " + request.getCodEnc()));
        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + request.getCodUsu()));
        UsuarioEncuesta asignacion = new UsuarioEncuesta();
        asignacion.setId(new UsuarioEncuestaId(request.getCodEnc(), request.getCodUsu()));
        asignacion.setEncuesta(encuesta);
        asignacion.setUsuario(usuario);
        return repository.save(asignacion);
    }

    @Transactional
    public void eliminar(Long codEnc, Long codUsu) {
        UsuarioEncuestaId id = new UsuarioEncuestaId(codEnc, codUsu);
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Asignación no encontrada.");
        repository.deleteById(id);
    }
}
