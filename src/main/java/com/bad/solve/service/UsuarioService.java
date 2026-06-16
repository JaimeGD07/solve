package com.bad.solve.service;

import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    public Usuario guardarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        if (usuario.getIntentos() == null) {
            usuario.setIntentos(0);
        }

        if (usuario.getEstado() == null) {
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {
        Usuario usuario = buscarPorId(id);

        usuario.setPrimNom(datos.getPrimNom());
        usuario.setSegNom(datos.getSegNom());
        usuario.setPrimApell(datos.getPrimApell());
        usuario.setSegApell(datos.getSegApell());
        usuario.setFechNac(datos.getFechNac());
        usuario.setPais(datos.getPais());
        usuario.setCiudad(datos.getCiudad());
        usuario.setEmail(datos.getEmail());
        usuario.setPassHash(datos.getPassHash());
        usuario.setIntentos(datos.getIntentos());
        usuario.setEstado(datos.getEstado());

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setEstado(0);
        return usuarioRepository.save(usuario);
    }
}
