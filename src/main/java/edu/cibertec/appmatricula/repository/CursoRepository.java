package edu.cibertec.appmatricula.repository;

import edu.cibertec.appmatricula.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCode(String code);
    List<Curso> findByActiveTrue();
    boolean existsByCode(String code);
}