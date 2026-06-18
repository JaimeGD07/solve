package com.bad.solve.service;

import com.bad.solve.dto.ResponderEncuestaRequest;
import com.bad.solve.dto.RespuestaItemRequest;
import com.bad.solve.entity.DetalleCatalogo;
import com.bad.solve.entity.Encuesta;
import com.bad.solve.entity.OpcionRespuesta;
import com.bad.solve.entity.Pregunta;
import com.bad.solve.entity.Respuesta;
import com.bad.solve.entity.RespuestaEncuesta;
import com.bad.solve.entity.Usuario;
import com.bad.solve.repository.DetalleCatalogoRepository;
import com.bad.solve.repository.EncuestaRepository;
import com.bad.solve.repository.OpcionRespuestaRepository;
import com.bad.solve.repository.PreguntaRepository;
import com.bad.solve.repository.RespuestaEncuestaRepository;
import com.bad.solve.repository.RespuestaRepository;
import com.bad.solve.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaEncuestaService {

    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final RespuestaRepository respuestaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EncuestaRepository encuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final OpcionRespuestaRepository opcionRespuestaRepository;
    private final DetalleCatalogoRepository detalleCatalogoRepository;

    public RespuestaEncuestaService(
            RespuestaEncuestaRepository respuestaEncuestaRepository,
            RespuestaRepository respuestaRepository,
            UsuarioRepository usuarioRepository,
            EncuestaRepository encuestaRepository,
            PreguntaRepository preguntaRepository,
            OpcionRespuestaRepository opcionRespuestaRepository,
            DetalleCatalogoRepository detalleCatalogoRepository) {
        this.respuestaEncuestaRepository = respuestaEncuestaRepository;
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.encuestaRepository = encuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.opcionRespuestaRepository = opcionRespuestaRepository;
        this.detalleCatalogoRepository = detalleCatalogoRepository;
    }

    public List<RespuestaEncuesta> listar() {
        return respuestaEncuestaRepository.findAll();
    }

    public RespuestaEncuesta buscarPorId(Long id) {
        return respuestaEncuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta de encuesta no encontrada"));
    }

    public RespuestaEncuesta guardar(RespuestaEncuesta respuestaEncuesta) {
        return respuestaEncuestaRepository.save(respuestaEncuesta);
    }

    public RespuestaEncuesta actualizar(Long id, RespuestaEncuesta datos) {
        RespuestaEncuesta respuestaEncuesta = buscarPorId(id);

        respuestaEncuesta.setEstado(datos.getEstado());
        respuestaEncuesta.setFechInicio(datos.getFechInicio());
        respuestaEncuesta.setFechFin(datos.getFechFin());
        respuestaEncuesta.setIntento(datos.getIntento());

        if (datos.getUsuario() != null) {
            respuestaEncuesta.setUsuario(datos.getUsuario());
        }

        if (datos.getEncuesta() != null) {
            respuestaEncuesta.setEncuesta(datos.getEncuesta());
        }

        return respuestaEncuestaRepository.save(respuestaEncuesta);
    }

    public void eliminar(Long id) {
        RespuestaEncuesta respuestaEncuesta = buscarPorId(id);
        respuestaEncuestaRepository.delete(respuestaEncuesta);
    }

    @Transactional
    public Long responderEncuesta(ResponderEncuestaRequest request) {

        if (request.getCodUsu() == null) {
            throw new RuntimeException("Debe enviar el código del usuario");
        }

        if (request.getCodEnc() == null) {
            throw new RuntimeException("Debe enviar el código de la encuesta");
        }

        if (request.getRespuestas() == null || request.getRespuestas().isEmpty()) {
            throw new RuntimeException("Debe enviar al menos una respuesta");
        }

        Usuario usuario = usuarioRepository.findById(request.getCodUsu())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Encuesta encuesta = encuestaRepository.findById(request.getCodEnc())
                .orElseThrow(() -> new RuntimeException("Encuesta no encontrada"));

        if (!"ACTIVO".equalsIgnoreCase(usuario.getEstado())) {
            throw new RuntimeException("El usuario no está activo");
        }

        Integer intento = respuestaEncuestaRepository.obtenerSiguienteIntento(
                usuario.getCodUsu(),
                encuesta.getCodEnc());

        RespuestaEncuesta respuestaEncuesta = new RespuestaEncuesta();
        respuestaEncuesta.setUsuario(usuario);
        respuestaEncuesta.setEncuesta(encuesta);
        LocalDateTime fechaInicio = LocalDateTime.now();

        respuestaEncuesta.setFechInicio(fechaInicio);
        respuestaEncuesta.setFechFin(null);
        respuestaEncuesta.setEstado("EN_PROCESO");
        respuestaEncuesta.setIntento(intento);

        RespuestaEncuesta encabezadoGuardado = respuestaEncuestaRepository.save(respuestaEncuesta);

        for (RespuestaItemRequest item : request.getRespuestas()) {

            if (item.getCodPre() == null) {
                throw new RuntimeException("Cada respuesta debe tener una pregunta");
            }

            Pregunta pregunta = preguntaRepository.findById(item.getCodPre())
                    .orElseThrow(() -> new RuntimeException("Pregunta no encontrada: " + item.getCodPre()));

            Respuesta respuesta = new Respuesta();
            respuesta.setRespuestaEncuesta(encabezadoGuardado);
            respuesta.setPregunta(pregunta);
            respuesta.setTextoResp(item.getTextoResp());
            respuesta.setValorResp(item.getValorResp());
            respuesta.setPosicionRank(item.getPosicionRank());

            if (item.getCodOpcResp() != null) {
                OpcionRespuesta opcionRespuesta = opcionRespuestaRepository.findById(item.getCodOpcResp())
                        .orElseThrow(() -> new RuntimeException(
                                "Opción de respuesta no encontrada: " + item.getCodOpcResp()));

                respuesta.setOpcionRespuesta(opcionRespuesta);
            }

            if (item.getCodDetCat() != null) {
                DetalleCatalogo detalleCatalogo = detalleCatalogoRepository.findById(item.getCodDetCat())
                        .orElseThrow(() -> new RuntimeException(
                                "Detalle de catálogo no encontrado: " + item.getCodDetCat()));

                respuesta.setDetalleCatalogo(detalleCatalogo);
            }

            respuestaRepository.save(respuesta);
        }

        encabezadoGuardado.setEstado("FINALIZADA");
        encabezadoGuardado.setFechFin(LocalDateTime.now().plusSeconds(1));
        respuestaEncuestaRepository.save(encabezadoGuardado);

        return encabezadoGuardado.getCodRespEnc();

    }
}