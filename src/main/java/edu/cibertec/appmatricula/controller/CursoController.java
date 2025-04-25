package edu.cibertec.appmatricula.controller;

import edu.cibertec.appmatricula.dto.CursoDTO;
import edu.cibertec.appmatricula.dto.CursoRequestDTO;
import edu.cibertec.appmatricula.service.CursoService;
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
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cursos", description = "API para la gestión de cursos")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Retorna una lista de todos los cursos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente")
    public ResponseEntity<List<CursoDTO>> findAll() {
        log.info("REST request to get all courses");
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/active")
    @Operation(summary = "Obtener cursos activos", description = "Retorna una lista de todos los cursos activos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos activos obtenida exitosamente")
    public ResponseEntity<List<CursoDTO>> findAllActive() {
        log.info("REST request to get all active courses");
        return ResponseEntity.ok(cursoService.findAllActive());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener curso por ID", description = "Retorna un curso según su ID")
    @ApiResponse(responseCode = "200", description = "Curso encontrado")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        log.info("REST request to get course by id: {}", id);
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Obtener curso por código", description = "Retorna un curso según su código")
    @ApiResponse(responseCode = "200", description = "Curso encontrado")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoDTO> findByCode(@PathVariable String code) {
        log.info("REST request to get course by code: {}", code);
        return ResponseEntity.ok(cursoService.findByCode(code));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "409", description = "El curso ya existe")
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
        log.info("REST request to create a new course");
        CursoDTO result = cursoService.create(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar curso", description = "Actualiza los datos de un curso existente")
    @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    @ApiResponse(responseCode = "409", description = "Conflicto, datos duplicados")
    public ResponseEntity<CursoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
        log.info("REST request to update course with id: {}", id);
        CursoDTO result = cursoService.update(id, cursoRequestDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar curso", description = "Elimina un curso según su ID")
    @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete course with id: {}", id);
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle-active")
    @Operation(summary = "Cambiar estado activo", description = "Activa o desactiva un curso según su estado actual")
    @ApiResponse(responseCode = "200", description = "Estado del curso cambiado exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoDTO> toggleActive(@PathVariable Long id) {
        log.info("REST request to toggle active status for course with id: {}", id);
        return ResponseEntity.ok(cursoService.toggleActive(id));
    }
}
