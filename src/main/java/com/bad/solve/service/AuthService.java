package com.bad.solve.service;

import com.bad.solve.dto.auth.AuthResponse;
import com.bad.solve.dto.auth.CambiarPasswordRequest;
import com.bad.solve.dto.auth.LoginRequest;
import com.bad.solve.dto.auth.MensajeResponse;
import com.bad.solve.dto.auth.RegistroRequest;
import com.bad.solve.entity.Identifica;
import com.bad.solve.entity.Rol;
import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.IdentificaRepository;
import com.bad.solve.repository.RolRepository;
import com.bad.solve.repository.UsuarioRepository;
import com.bad.solve.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final IdentificaRepository identificaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            IdentificaRepository identificaRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.identificaRepository = identificaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {

    Usuario usuario = usuarioRepository.findByEmailIgnoreCase(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

    if ("BLOQUEADO".equalsIgnoreCase(usuario.getEstado())) {
        throw new RuntimeException("Usuario bloqueado por demasiados intentos fallidos");
    }

    boolean passwordCorrecta = passwordEncoder.matches(
            request.getPassword(),
            usuario.getPassHash()
    );

    if (!passwordCorrecta) {
        int intentosActuales = usuario.getIntentos() == null ? 0 : usuario.getIntentos();
        int nuevosIntentos = intentosActuales + 1;

        usuario.setIntentos(nuevosIntentos);

        if (nuevosIntentos >= 3) {
            usuario.setEstado("BLOQUEADO");
        }

        usuarioRepository.saveAndFlush(usuario);

        throw new RuntimeException("Credenciales incorrectas");
    }

    usuario.setIntentos(0);
    usuarioRepository.saveAndFlush(usuario);

    List<String> roles = usuarioRepository.buscarRolesPorUsuario(usuario.getCodUsu());

    String token = jwtService.generarToken(
            usuario.getEmail(),
            usuario.getCodUsu(),
            roles
    );

    return construirAuthResponse(usuario, token, roles);
}

    @Transactional
    public MensajeResponse registrar(RegistroRequest request) {

        if (usuarioRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setPrimNom(request.getPrimNom());
        usuario.setSegNom(request.getSegNom());
        usuario.setPrimApell(request.getPrimApell());
        usuario.setSegApell(request.getSegApell());
        usuario.setFechNac(request.getFechNac());
        usuario.setPais(request.getPais());
        usuario.setCiudad(request.getCiudad());
        usuario.setEmail(request.getEmail());
        usuario.setPassHash(passwordEncoder.encode(request.getPassword()));
        usuario.setIntentos(0);
        usuario.setEstado("ACTIVO");

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        if (request.getCodRol() != null) {
            Rol rol = rolRepository.findById(request.getCodRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            Identifica identifica = new Identifica();
            identifica.setUsuario(usuarioGuardado);
            identifica.setRol(rol);

            identificaRepository.save(identifica);
        }

        return new MensajeResponse("Usuario registrado correctamente");
    }

    @Transactional
    public MensajeResponse cambiarPassword(CambiarPasswordRequest request) {

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordActualCorrecta = passwordEncoder.matches(
                request.getPasswordActual(),
                usuario.getPassHash()
        );

        if (!passwordActualCorrecta) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        usuario.setPassHash(passwordEncoder.encode(request.getPasswordNueva()));
        usuarioRepository.save(usuario);

        return new MensajeResponse("Contraseña actualizada correctamente");
    }

    public AuthResponse obtenerPerfil(String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no válido");
        }

        String token = authHeader.substring(7);
        String email = jwtService.extraerEmail(token);

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> roles = usuarioRepository.buscarRolesPorUsuario(usuario.getCodUsu());

        return construirAuthResponse(usuario, token, roles);
    }

    private AuthResponse construirAuthResponse(
            Usuario usuario,
            String token,
            List<String> roles
    ) {
        String nombreCompleto = (
                usuario.getPrimNom() + " " +
                (usuario.getSegNom() == null ? "" : usuario.getSegNom() + " ") +
                usuario.getPrimApell() + " " +
                (usuario.getSegApell() == null ? "" : usuario.getSegApell())
        ).trim();

        return new AuthResponse(
                token,
                "Bearer",
                usuario.getCodUsu(),
                nombreCompleto,
                usuario.getEmail(),
                usuario.getEstado(),
                roles
        );
    }
}