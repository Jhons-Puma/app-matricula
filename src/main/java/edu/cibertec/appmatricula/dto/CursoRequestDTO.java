package edu.cibertec.appmatricula.dto;

public record CursoRequestDTO(
        String name,
        String code,
        String description,
        Integer credits,
        Integer hours
) {
}
