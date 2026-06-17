package com.bad.solve.dto;

/**
 * DTO: RESPUESTA ENCUESTA REQUEST
 * 
 * Objeto de transferencia para INICIAR una sesión de respuesta de encuesta.
 * Se usa cuando un usuario comienza a responder una encuesta.
 * 
 * USO EN FRONTEND:
 * 
 * INICIAR ENCUESTA:
 * POST /api/respuestas-encuesta/iniciar
 * Body: {
 *   "codEnc": 5,      // ID de la encuesta a responder
 *   "codUsu": 12      // ID del usuario que responde
 * }
 * Respuesta: RespuestaEncuesta creada con:
 *   - codRespEnc: ID de la sesión (guardar para próximas peticiones)
 *   - estado: "EN_PROCESO"
 *   - fechInicio: Timestamp automático
 * 
 * FLUJO EN FRONTEND:
 * 
 * 1. Usuario selecciona encuesta a responder:
 *    GET /api/asignaciones-encuesta?codUsu={id}
 *    Seleccionar una encuesta de la lista
 * 
 * 2. Iniciar respuesta:
 *    POST /api/respuestas-encuesta/iniciar
 *    Body: { "codEnc": {id_encuesta}, "codUsu": {id_usuario} }
 *    Guardar codRespEnc de la respuesta
 * 
 * 3. Obtener preguntas:
 *    GET /api/preguntas?codEnc={id_encuesta}
 * 
 * 4. Mostrar formulario de respuesta:
 *    Para cada pregunta, mostrar según su tipo
 * 
 * 5. Guardar respuestas (múltiples veces):
 *    POST /api/respuestas
 *    Body: { "codRespEnc": {guardado}, "codPre": {...}, "textoResp": "{respuesta}" }
 * 
 * 6. Finalizar encuesta:
 *    PUT /api/respuestas-encuesta/{codRespEnc}/finalizar
 *    Respuesta: RespuestaEncuesta con estado="FINALIZADO"
 * 
 * CAMPOS:
 * 
 * - codEnc (Long, OBLIGATORIO)
 *   ID de la encuesta que el usuario va a responder
 *   Obtener de: GET /api/encuestas o GET /api/asignaciones-encuesta?codUsu={id}
 *   Ejemplo: 1, 2, 3...
 * 
 * - codUsu (Long, OBLIGATORIO)
 *   ID del usuario que está respondiendo
 *   Obtener del login/sesión del usuario actual
 *   Ejemplo: 12, 45, 89...
 * 
 * VALIDACIONES:
 * - codEnc: Debe existir y debe estar asignada al usuario
 * - codUsu: Debe existir y estar activo
 * - Usuario debe tener permiso para responder esta encuesta
 * 
 * ERRORES COMUNES:
 * - 404: Encuesta no existe (codEnc inválido)
 * - 404: Usuario no existe (codUsu inválido)
 * - 403: Usuario no tiene permiso (encuesta no asignada)
 * - 409: Ya existe sesión activa para este usuario-encuesta
 * 
 * ESTADOS POSIBLES DESPUÉS DE CREAR:
 * - EN_PROCESO: Usuario está respondiendo (estado por defecto)
 * - FINALIZADO: Usuario ya envió respuestas (si actualiza estado)
 * - ANULADO: Usuario canceló la encuesta (si actualiza estado)
 */
public class RespuestaEncuestaRequest {
    private Long codEnc;
    private Long codUsu;

    public Long getCodEnc() {
        return codEnc;
    }

    public void setCodEnc(Long codEnc) {
        this.codEnc = codEnc;
    }

    public Long getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(Long codUsu) {
        this.codUsu = codUsu;
    }
}
