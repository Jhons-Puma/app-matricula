package edu.cibertec.appmatricula.controller;


import edu.cibertec.appmatricula.dto.UsuarioDTO;
import edu.cibertec.appmatricula.dto.UsuarioRequestDTO;
import edu.cibertec.appmatricula.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        log.info("REST request to get all users");
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        log.info("REST request to get user by id: {}", id);
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Obtener usuario por username", description = "Retorna un usuario según su nombre de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UsuarioDTO> findByUsername(@PathVariable String username) {
        log.info("REST request to get user by username: {}", username);
        return ResponseEntity.ok(usuarioService.findByUsername(username));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        log.info("REST request to create a new user");
        UsuarioDTO result = usuarioService.create(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "409", description = "Conflicto, datos duplicados")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        log.info("REST request to update user with id: {}", id);
        UsuarioDTO result = usuarioService.update(id, usuarioRequestDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario según su ID")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete user with id: {}", id);
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle-active")
    @Operation(summary = "Cambiar estado activo", description = "Activa o desactiva un usuario según su estado actual")
    @ApiResponse(responseCode = "200", description = "Estado del usuario cambiado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UsuarioDTO> toggleActive(@PathVariable Long id) {
        log.info("REST request to toggle active status for user with id: {}", id);
        return ResponseEntity.ok(usuarioService.toggleActive(id));
    }
}
