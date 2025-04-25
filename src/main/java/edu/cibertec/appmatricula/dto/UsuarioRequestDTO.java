package edu.cibertec.appmatricula.dto;

public record UsuarioRequestDTO(
        String username,
        String password,
        String email,
        String fullName,
        String role
) {
}
