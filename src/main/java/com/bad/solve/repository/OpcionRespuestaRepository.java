package com.bad.solve.repository;

import com.bad.solve.entity.OpcionRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OpcionRespuestaRepository extends JpaRepository<OpcionRespuesta, Long> {
    List<OpcionRespuesta> findByPreguntaCodPreOrderByOrdenAsc(Long codPre);
}
