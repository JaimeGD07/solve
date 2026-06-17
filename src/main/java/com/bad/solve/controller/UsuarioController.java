package com.bad.solve.controller;

import com.bad.solve.entity.Usuario;
import com.bad.solve.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * Lista todos los usuarios del sistema
     * 
     * @return Lista de todos los usuarios
     */
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    /**
     * Obtiene un usuario específico por su ID
     * 
     * @param id - Código del usuario a obtener
     * @return Usuario solicitado
     */
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Crea un nuevo usuario
     * 
     * @param usuario - Objeto con los datos del usuario
     * @return Usuario creado con ID asignado
     */
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crear(usuario);
    }

    /**
     * Actualiza un usuario existente
     * 
     * @param id - Código del usuario a actualizar
     * @param usuario - Objeto con los nuevos datos
     * @return Usuario actualizado
     */
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return service.actualizar(id, usuario);
    }

    /**
     * Elimina un usuario del sistema
     * 
     * @param id - Código del usuario a eliminar
     * @return Respuesta vacía (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
