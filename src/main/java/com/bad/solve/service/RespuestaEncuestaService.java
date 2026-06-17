package com.bad.solve.service;

import com.bad.solve.dto.RespuestaEncuestaRequest;
import com.bad.solve.entity.Encuesta;
import com.bad.solve.entity.RespuestaEncuesta;
import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.EncuestaRepository;
import com.bad.solve.repository.RespuestaEncuestaRepository;
import com.bad.solve.repository.UsuarioEncuestaRepository;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaEncuestaService {
    private final RespuestaEncuestaRepository repository;
    private final EncuestaRepository encuestaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEncuestaRepository usuarioEncuestaRepository;

    public RespuestaEncuestaService(RespuestaEncuestaRepository repository, EncuestaRepository encuestaRepository,
            UsuarioRepository usuarioRepository, UsuarioEncuestaRepository usuarioEncuestaRepository) {
        this.repository = repository;
        this.encuestaRepository = encuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioEncuestaRepository = usuarioEncuestaRepository;
    }

    public List<RespuestaEncuesta> listar() {
        return repository.findAll();
    }

    public List<RespuestaEncuesta> listarPorUsuario(Long codUsu) {
        return repository.findByUsuarioCodUsu(codUsu);
    }

    public List<RespuestaEncuesta> listarPorEncuesta(Long codEnc) {
        return repository.findByEncuestaCodEnc(codEnc);
    }

    public RespuestaEncuesta obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta de encuesta no encontrada: " + id));
    }

    @Transactional
    public RespuestaEncuesta iniciar(RespuestaEncuestaRequest request) {
        Encuesta encuesta = encuestaRepository.findById(request.getCodEnc())
                .orElseThrow(() -> new EntityNotFoundException("Encuesta no encontrada: " + request.getCodEnc()));
        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + request.getCodUsu()));

        if (!"ACTIVO".equals(usuario.getEstado())) {
            throw new IllegalStateException("El usuario no está activo. Estado actual: " + usuario.getEstado());
        }

        boolean asignada = usuarioEncuestaRepository.existsByEncuestaCodEncAndUsuarioCodUsu(request.getCodEnc(),
                request.getCodUsu());
        if (!asignada) {
            throw new IllegalStateException("La encuesta no está asignada al usuario.");
        }

        Integer ultimoIntento = repository.obtenerUltimoIntento(request.getCodEnc(), request.getCodUsu());
        RespuestaEncuesta respuestaEncuesta = new RespuestaEncuesta();
        respuestaEncuesta.setEncuesta(encuesta);
        respuestaEncuesta.setUsuario(usuario);
        respuestaEncuesta.setEstado("EN_PROCESO");
        respuestaEncuesta.setIntento((ultimoIntento == null ? 0 : ultimoIntento) + 1);
        return repository.save(respuestaEncuesta);
    }

    @Transactional
    public RespuestaEncuesta finalizar(Long id) {
        RespuestaEncuesta respuestaEncuesta = obtener(id);
        respuestaEncuesta.setEstado("FINALIZADA");
        respuestaEncuesta.setFechFin(LocalDateTime.now());
        return repository.save(respuestaEncuesta);
    }

    @Transactional
    public RespuestaEncuesta anular(Long id) {
        RespuestaEncuesta respuestaEncuesta = obtener(id);
        respuestaEncuesta.setEstado("ANULADA");
        respuestaEncuesta.setFechFin(LocalDateTime.now());
        return repository.save(respuestaEncuesta);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }
}
