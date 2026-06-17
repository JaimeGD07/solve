package com.bad.solve.repository;

import com.bad.solve.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByEncuestaCodEnc(Long codEnc);
}
