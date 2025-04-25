package edu.cibertec.appmatricula.service.impl;

import edu.cibertec.appmatricula.dto.UsuarioDTO;
import edu.cibertec.appmatricula.dto.UsuarioRequestDTO;
import edu.cibertec.appmatricula.exception.ResourceAlreadyExistsException;
import edu.cibertec.appmatricula.exception.ResourceNotFoundException;
import edu.cibertec.appmatricula.mapper.UsuarioMapper;
import edu.cibertec.appmatricula.model.Usuario;
import edu.cibertec.appmatricula.repository.UsuarioRepository;
import edu.cibertec.appmatricula.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDTO> findAll() {
        log.info("Finding all users");
        return usuarioMapper.toDtoList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioDTO findById(Long id) {
        log.info("Finding user with id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
        log.info("Finding user with username: {}", username);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "username", username));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioRequestDTO usuarioRequestDTO) {
        log.info("Creating new user with username: {}", usuarioRequestDTO.username());

        // Verificar si el username ya existe
        if (usuarioRepository.existsByUsername(usuarioRequestDTO.username())) {
            throw new ResourceAlreadyExistsException("Usuario", "username", usuarioRequestDTO.username());
        }

        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.email())) {
            throw new ResourceAlreadyExistsException("Usuario", "email", usuarioRequestDTO.email());
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        // En una aplicación real, aquí se encriptaría la contraseña antes de guardarla

        usuario = usuarioRepository.save(usuario);
        log.info("User created successfully with id: {}", usuario.getId());
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        log.info("Updating user with id: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        // Verificar si el username ya existe y no es el mismo usuario
        if (!usuario.getUsername().equals(usuarioRequestDTO.username()) &&
                usuarioRepository.existsByUsername(usuarioRequestDTO.username())) {
            throw new ResourceAlreadyExistsException("Usuario", "username", usuarioRequestDTO.username());
        }

        // Verificar si el email ya existe y no es el mismo usuario
        if (!usuario.getEmail().equals(usuarioRequestDTO.email()) &&
                usuarioRepository.existsByEmail(usuarioRequestDTO.email())) {
            throw new ResourceAlreadyExistsException("Usuario", "email", usuarioRequestDTO.email());
        }

        usuarioMapper.updateEntityFromDto(usuarioRequestDTO, usuario);

        // En una aplicación real, aquí se verificaría si la contraseña cambió para encriptarla

        usuario = usuarioRepository.save(usuario);
        log.info("User updated successfully");
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting user with id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        usuarioRepository.delete(usuario);
        log.info("User deleted successfully");
    }

    @Override
    @Transactional
    public UsuarioDTO toggleActive(Long id) {
        log.info("Toggling active status for user with id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        usuario.setActive(!usuario.getActive());
        usuario = usuarioRepository.save(usuario);

        log.info("User active status toggled to: {}", usuario.getActive());
        return usuarioMapper.toDto(usuario);
    }
}
