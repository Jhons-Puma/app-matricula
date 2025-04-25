package edu.cibertec.appmatricula.service;


import edu.cibertec.appmatricula.dto.CursoDTO;
import edu.cibertec.appmatricula.dto.CursoRequestDTO;

import java.util.List;

public interface CursoService {
    List<CursoDTO> findAll();
    List<CursoDTO> findAllActive();
    CursoDTO findById(Long id);
    CursoDTO findByCode(String code);
    CursoDTO create(CursoRequestDTO cursoRequestDTO);
    CursoDTO update(Long id, CursoRequestDTO cursoRequestDTO);
    void delete(Long id);
    CursoDTO toggleActive(Long id);
}
