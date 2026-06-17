package com.bad.solve.service;

import com.bad.solve.dto.AsignacionRolRequest;
import com.bad.solve.entity.Identifica;
import com.bad.solve.entity.Rol;
import com.bad.solve.entity.Usuario;
import com.bad.solve.id.IdentificaId;
import com.bad.solve.repository.IdentificaRepository;
import com.bad.solve.repository.RolRepository;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class IdentificaService {
    private final IdentificaRepository repository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    public IdentificaService(IdentificaRepository repository, RolRepository rolRepository,
            UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Identifica> listar() {
        return repository.findAll();
    }

    public List<Identifica> listarPorUsuario(Long codUsu) {
        return repository.findByUsuarioCodUsu(codUsu);
    }

    @Transactional
    public Identifica asignar(AsignacionRolRequest request) {
        Rol rol = rolRepository.findById(request.getCodRol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado: " + request.getCodRol()));
        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + request.getCodUsu()));
        Identifica asignacion = new Identifica();
        asignacion.setId(new IdentificaId(request.getCodRol(), request.getCodUsu()));
        asignacion.setRol(rol);
        asignacion.setUsuario(usuario);
        return repository.save(asignacion);
    }

    @Transactional
    public void eliminar(Long codRol, Long codUsu) {
        IdentificaId id = new IdentificaId(codRol, codUsu);
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Asignación de rol no encontrada.");
        repository.deleteById(id);
    }
}
