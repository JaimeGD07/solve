package com.bad.solve.service;

import com.bad.solve.dto.CrearEncuestaCompletaRequest;
import com.bad.solve.dto.OpcionCompletaRequest;
import com.bad.solve.dto.PreguntaCompletaRequest;
import com.bad.solve.entity.Encuesta;
import com.bad.solve.entity.OpcionRespuesta;
import com.bad.solve.entity.Pregunta;
import com.bad.solve.entity.TipoPregunta;
import com.bad.solve.repository.EncuestaRepository;
import com.bad.solve.repository.OpcionRespuestaRepository;
import com.bad.solve.repository.PreguntaRepository;
import com.bad.solve.repository.TipoPreguntaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EncuestaCompletaService {

  private final EncuestaRepository encuestaRepository;
  private final PreguntaRepository preguntaRepository;
  private final OpcionRespuestaRepository opcionRespuestaRepository;
  private final TipoPreguntaRepository tipoPreguntaRepository;

  public EncuestaCompletaService(
      EncuestaRepository encuestaRepository,
      PreguntaRepository preguntaRepository,
      OpcionRespuestaRepository opcionRespuestaRepository,
      TipoPreguntaRepository tipoPreguntaRepository) {
    this.encuestaRepository = encuestaRepository;
    this.preguntaRepository = preguntaRepository;
    this.opcionRespuestaRepository = opcionRespuestaRepository;
    this.tipoPreguntaRepository = tipoPreguntaRepository;
  }

  @Transactional
  public Long crearEncuestaCompleta(CrearEncuestaCompletaRequest request) {

    if (request.getTitulo() == null || request.getTitulo().isBlank()) {
      throw new RuntimeException("El título de la encuesta es obligatorio");
    }

    if (request.getPreguntas() == null || request.getPreguntas().isEmpty()) {
      throw new RuntimeException("Debe agregar al menos una pregunta");
    }

    Encuesta encuesta = new Encuesta();
    encuesta.setTitulo(request.getTitulo());
    encuesta.setDescripcion(request.getDescripcion());
    encuesta.setNumPreguntas(request.getPreguntas().size());

    Encuesta encuestaGuardada = encuestaRepository.save(encuesta);

    for (PreguntaCompletaRequest preguntaRequest : request.getPreguntas()) {

      String tipoNormalizado = obtenerTipoNormalizado(preguntaRequest);

      TipoPregunta tipoPregunta = tipoPreguntaRepository
          .findByNombreIgnoreCase(tipoNormalizado)
          .orElseThrow(() -> new RuntimeException(
              "Tipo de pregunta no encontrado: " + tipoNormalizado));

      Pregunta pregunta = new Pregunta();
      pregunta.setEncuesta(encuestaGuardada);
      pregunta.setTipoPregunta(tipoPregunta);

      // Usa aquí el setter correcto que ya corregiste
      pregunta.setEnunciado(preguntaRequest.getTexto());

      pregunta.setObligatoria(
          preguntaRequest.getObligatoria() != null && preguntaRequest.getObligatoria()
              ? 1
              : 0);

      Pregunta preguntaGuardada = preguntaRepository.save(pregunta);

      if (preguntaRequest.getOpciones() != null) {
        int orden = 1;

        for (OpcionCompletaRequest opcionRequest : preguntaRequest.getOpciones()) {

          if (opcionRequest.getTexto() == null || opcionRequest.getTexto().isBlank()) {
            continue;
          }

          OpcionRespuesta opcion = new OpcionRespuesta();
          opcion.setPregunta(preguntaGuardada);

          // Usa aquí el setter correcto que ya corregiste
          opcion.setOpcion(opcionRequest.getTexto());

          if (opcionRequest.getValor() != null) {
            opcion.setValor(opcionRequest.getValor());
          }

          if (opcionRequest.getOrden() != null) {
            opcion.setOrden(opcionRequest.getOrden());
          } else {
            opcion.setOrden(orden);
          }

          opcionRespuestaRepository.save(opcion);
          orden++;
        }
      }
    }

    return encuestaGuardada.getCodEnc();
  }

  private String normalizarTipoPregunta(String tipo) {
    return tipo
        .trim()
        .toUpperCase()
        .replace(" ", "_")
        .replace("-", "_")
        .replace("LIKERT_1_5", "ESCALA_LIKERT")
        .replace("ESCALA_LIKERT_1_5", "ESCALA_LIKERT");
  }
  private String obtenerTipoNormalizado(PreguntaCompletaRequest preguntaRequest) {
    String tipo = preguntaRequest.getTipo();

    if (tipo == null || tipo.isBlank()) {
        throw new RuntimeException("El tipo de pregunta es obligatorio. Revise el JSON enviado desde Angular.");
    }

    return normalizarTipoPregunta(tipo);
}
}