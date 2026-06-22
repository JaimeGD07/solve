package com.bad.solve.repository;

import com.bad.solve.entity.TipoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoPreguntaRepository extends JpaRepository<TipoPregunta, Long> {

    Optional<TipoPregunta> findByNombreIgnoreCase(String nombre);
}
