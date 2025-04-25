package edu.cibertec.appmatricula.dto;

import java.time.LocalDateTime;

public record UsuarioDTO(
        Long id,
        String username,
        String email,
        String fullName,
        String role,
        Boolean active,
        LocalDateTime createdAt
) {
}
