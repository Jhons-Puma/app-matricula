package edu.cibertec.appmatricula.service.impl;

import edu.cibertec.appmatricula.dto.CursoDTO;
import edu.cibertec.appmatricula.dto.CursoRequestDTO;
import edu.cibertec.appmatricula.exception.ResourceAlreadyExistsException;
import edu.cibertec.appmatricula.exception.ResourceNotFoundException;
import edu.cibertec.appmatricula.mapper.CursoMapper;
import edu.cibertec.appmatricula.model.Curso;
import edu.cibertec.appmatricula.repository.CursoRepository;
import edu.cibertec.appmatricula.service.CursoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    public List<CursoDTO> findAll() {
        log.info("Finding all courses");
        return cursoMapper.toDtoList(cursoRepository.findAll());
    }

    @Override
    public List<CursoDTO> findAllActive() {
        log.info("Finding all active courses");
        return cursoMapper.toDtoList(cursoRepository.findByActiveTrue());
    }

    @Override
    public CursoDTO findById(Long id) {
        log.info("Finding course with id: {}", id);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));
        return cursoMapper.toDto(curso);
    }

    @Override
    public CursoDTO findByCode(String code) {
        log.info("Finding course with code: {}", code);
        Curso curso = cursoRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "code", code));
        return cursoMapper.toDto(curso);
    }

    @Override
    @Transactional
    public CursoDTO create(CursoRequestDTO cursoRequestDTO) {
        log.info("Creating new course with code: {}", cursoRequestDTO.code());

        // Verificar si el código ya existe
        if (cursoRepository.existsByCode(cursoRequestDTO.code())) {
            throw new ResourceAlreadyExistsException("Curso", "code", cursoRequestDTO.code());
        }

        Curso curso = cursoMapper.toEntity(cursoRequestDTO);
        curso = cursoRepository.save(curso);

        log.info("Course created successfully with id: {}", curso.getId());
        return cursoMapper.toDto(curso);
    }

    @Override
    @Transactional
    public CursoDTO update(Long id, CursoRequestDTO cursoRequestDTO) {
        log.info("Updating course with id: {}", id);

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));

        // Verificar si el código ya existe y no es el mismo curso
        if (!curso.getCode().equals(cursoRequestDTO.code()) &&
                cursoRepository.existsByCode(cursoRequestDTO.code())) {
            throw new ResourceAlreadyExistsException("Curso", "code", cursoRequestDTO.code());
        }

        cursoMapper.updateEntityFromDto(cursoRequestDTO, curso);
        curso = cursoRepository.save(curso);

        log.info("Course updated successfully");
        return cursoMapper.toDto(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting course with id: {}", id);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));
        cursoRepository.delete(curso);
        log.info("Course deleted successfully");
    }

    @Override
    @Transactional
    public CursoDTO toggleActive(Long id) {
        log.info("Toggling active status for course with id: {}", id);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));

        curso.setActive(!curso.getActive());
        curso = cursoRepository.save(curso);

        log.info("Course active status toggled to: {}", curso.getActive());
        return cursoMapper.toDto(curso);
    }
}
