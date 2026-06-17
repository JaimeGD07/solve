package com.bad.solve.controller;

import com.bad.solve.dto.auth.AuthResponse;
import com.bad.solve.dto.auth.CambiarPasswordRequest;
import com.bad.solve.dto.auth.LoginRequest;
import com.bad.solve.dto.auth.MensajeResponse;
import com.bad.solve.dto.auth.RegistroRequest;
import com.bad.solve.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<MensajeResponse> registrar(@RequestBody RegistroRequest request) {
        return ResponseEntity.ok(authService.registrar(request));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeResponse> cambiarPassword(
            @RequestBody CambiarPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.cambiarPassword(request));
    }

    @GetMapping("/perfil")
    public ResponseEntity<AuthResponse> obtenerPerfil(
            @RequestHeader("Authorization") String authHeader
    ) {
        return ResponseEntity.ok(authService.obtenerPerfil(authHeader));
    }
}