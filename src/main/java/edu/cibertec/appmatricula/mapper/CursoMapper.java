package edu.cibertec.appmatricula.mapper;

import edu.cibertec.appmatricula.dto.CursoDTO;
import edu.cibertec.appmatricula.dto.CursoRequestDTO;
import edu.cibertec.appmatricula.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoDTO toDto(Curso curso);

    List<CursoDTO> toDtoList(List<Curso> cursos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    Curso toEntity(CursoRequestDTO cursoRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(CursoRequestDTO dto, @MappingTarget Curso curso);
}
