package edu.cibertec.appmatricula.service;


import edu.cibertec.appmatricula.dto.UsuarioDTO;
import edu.cibertec.appmatricula.dto.UsuarioRequestDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(Long id);
    UsuarioDTO findByUsername(String username);
    UsuarioDTO create(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO);
    void delete(Long id);
    UsuarioDTO toggleActive(Long id);
}
