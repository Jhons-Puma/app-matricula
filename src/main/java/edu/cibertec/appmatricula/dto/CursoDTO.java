package edu.cibertec.appmatricula.dto;

import java.time.LocalDateTime;

public record CursoDTO(
        Long id,
        String name,
        String code,
        String description,
        Integer credits,
        Integer hours,
        Boolean active,
        LocalDateTime createdAt
) {
}
