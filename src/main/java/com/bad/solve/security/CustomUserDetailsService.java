package com.bad.solve.security;

import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<SimpleGrantedAuthority> authorities = usuarioRepository
                .buscarRolesPorUsuario(usuario.getCodUsu())
                .stream()
                .map(rol -> rol.startsWith("ROLE_") ? rol : "ROLE_" + rol)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassHash())
                .authorities(authorities)
                .disabled(!"ACTIVO".equalsIgnoreCase(usuario.getEstado()))
                .build();
    }
}