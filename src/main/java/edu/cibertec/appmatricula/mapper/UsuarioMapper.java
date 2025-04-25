package edu.cibertec.appmatricula.mapper;

import edu.cibertec.appmatricula.dto.UsuarioDTO;
import edu.cibertec.appmatricula.dto.UsuarioRequestDTO;
import edu.cibertec.appmatricula.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);

    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(UsuarioRequestDTO dto, @MappingTarget Usuario usuario);
}
