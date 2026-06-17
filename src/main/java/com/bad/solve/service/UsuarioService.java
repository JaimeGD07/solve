package com.bad.solve.service;

import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtener(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + id));
    }

    @Transactional
    public Usuario crear(Usuario usuario) {
        if (usuario.getIntentos() == null)
            usuario.setIntentos(0);
        if (usuario.getEstado() == null || usuario.getEstado().isBlank())
            usuario.setEstado("ACTIVO");
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Long id, Usuario datos) {
        Usuario usuario = obtener(id);
        usuario.setPrimNom(datos.getPrimNom());
        usuario.setSegNom(datos.getSegNom());
        usuario.setPrimApell(datos.getPrimApell());
        usuario.setSegApell(datos.getSegApell());
        usuario.setFechNac(datos.getFechNac());
        usuario.setPais(datos.getPais());
        usuario.setCiudad(datos.getCiudad());
        usuario.setEmail(datos.getEmail());
        usuario.setIntentos(datos.getIntentos() == null ? usuario.getIntentos() : datos.getIntentos());
        usuario.setEstado(datos.getEstado() == null ? usuario.getEstado() : datos.getEstado());
        if (datos.getPassHash() != null && !datos.getPassHash().isBlank()) {
            usuario.setPassHash(datos.getPassHash());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.delete(obtener(id));
    }
}
