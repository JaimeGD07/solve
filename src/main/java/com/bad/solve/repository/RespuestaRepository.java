package com.bad.solve.repository;

import com.bad.solve.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByRespuestaEncuestaCodRespEnc(Long codRespEnc);
}
