package com.bad.solve.repository;

import com.bad.solve.entity.Token;
import com.bad.solve.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findByUsuarioAndTipoAndUtilizado(
            Usuario usuario,
            String tipo,
            Integer utilizado
    );
}