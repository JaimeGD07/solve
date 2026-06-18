package com.bad.solve.service;

import com.bad.solve.dto.ResponderEncuestaRequest;
import com.bad.solve.dto.RespuestaItemRequest;
import com.bad.solve.entity.*;
import com.bad.solve.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponderEncuestaService {

    private final UsuarioRepository usuarioRepository;
    private final EncuestaRepository encuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final RespuestaRepository respuestaRepository;
    private final OpcionRespuestaRepository opcionRespuestaRepository;
    private final DetalleCatalogoRepository detalleCatalogoRepository;

    public ResponderEncuestaService(
            UsuarioRepository usuarioRepository,
            EncuestaRepository encuestaRepository,
            PreguntaRepository preguntaRepository,
            RespuestaEncuestaRepository respuestaEncuestaRepository,
            RespuestaRepository respuestaRepository,
            OpcionRespuestaRepository opcionRespuestaRepository,
            DetalleCatalogoRepository detalleCatalogoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.encuestaRepository = encuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.respuestaEncuestaRepository = respuestaEncuestaRepository;
        this.respuestaRepository = respuestaRepository;
        this.opcionRespuestaRepository = opcionRespuestaRepository;
        this.detalleCatalogoRepository = detalleCatalogoRepository;
    }

    @Transactional
    public Long responder(ResponderEncuestaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Encuesta encuesta = encuestaRepository.findById(request.getCodEnc())
                .orElseThrow(() -> new RuntimeException("Encuesta no encontrada"));

        if (!"ACTIVO".equalsIgnoreCase(usuario.getEstado())) {
            throw new RuntimeException("El usuario no está activo");
        }

        if (request.getRespuestas() == null || request.getRespuestas().isEmpty()) {
            throw new RuntimeException("Debe enviar al menos una respuesta");
        }

        RespuestaEncuesta respuestaEncuesta = new RespuestaEncuesta();
        respuestaEncuesta.setUsuario(usuario);
        respuestaEncuesta.setEncuesta(encuesta);
        respuestaEncuesta.setFechInicio(LocalDateTime.now());
        respuestaEncuesta.setFechFin(LocalDateTime.now());
        respuestaEncuesta.setEstado("FINALIZADA");
        Integer intento = respuestaEncuestaRepository.obtenerSiguienteIntento(
                usuario.getCodUsu(),
                encuesta.getCodEnc());

        respuestaEncuesta.setIntento(intento);

        respuestaEncuesta.setIntento(intento);

        RespuestaEncuesta encabezadoGuardado = respuestaEncuestaRepository.save(respuestaEncuesta);

        for (RespuestaItemRequest item : request.getRespuestas()) {

            Pregunta pregunta = preguntaRepository.findById(item.getCodPre())
                    .orElseThrow(() -> new RuntimeException("Pregunta no encontrada: " + item.getCodPre()));

            Respuesta respuesta = new Respuesta();
            respuesta.setRespuestaEncuesta(encabezadoGuardado);
            respuesta.setPregunta(pregunta);
            respuesta.setTextoResp(item.getTextoResp());
            respuesta.setValorResp(item.getValorResp());
            respuesta.setPosicionRank(item.getPosicionRank());

            if (item.getCodOpcResp() != null) {
                OpcionRespuesta opcion = opcionRespuestaRepository.findById(item.getCodOpcResp())
                        .orElseThrow(() -> new RuntimeException("Opción no encontrada: " + item.getCodOpcResp()));
                respuesta.setOpcionRespuesta(opcion);
            }

            if (item.getCodDetCat() != null) {
                DetalleCatalogo detalle = detalleCatalogoRepository.findById(item.getCodDetCat())
                        .orElseThrow(
                                () -> new RuntimeException("Detalle catálogo no encontrado: " + item.getCodDetCat()));
                respuesta.setDetalleCatalogo(detalle);
            }

            respuestaRepository.save(respuesta);
        }

        return encabezadoGuardado.getCodRespEnc();
    }
}