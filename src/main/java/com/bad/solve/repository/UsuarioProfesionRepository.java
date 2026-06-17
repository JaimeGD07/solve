package com.bad.solve.repository;

import com.bad.solve.entity.UsuarioProfesion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioProfesionRepository extends JpaRepository<UsuarioProfesion, Long> {
    List<UsuarioProfesion> findByUsuarioCodUsu(Long codUsu);
}
