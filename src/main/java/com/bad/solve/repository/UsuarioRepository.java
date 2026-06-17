package com.bad.solve.repository;

import com.bad.solve.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

    @Query(value = """
        SELECT r.NOMBRE
        FROM ROL r
        INNER JOIN IDENTIFICA i ON i.COD_ROL = r.COD_ROL
        WHERE i.COD_USU = :codUsu
        """, nativeQuery = true)
    List<String> buscarRolesPorUsuario(@Param("codUsu") Long codUsu);
}