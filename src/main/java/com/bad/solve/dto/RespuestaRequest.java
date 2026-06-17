package com.bad.solve.dto;

/**
 * DTO: RESPUESTA REQUEST
 * 
 * Objeto de transferencia para guardar la respuesta de un usuario a una pregunta.
 * Captura diferentes tipos de respuestas según el tipo de pregunta.
 * 
 * USO EN FRONTEND:
 * 
 * GUARDAR RESPUESTA DE USUARIO:
 * POST /api/respuestas
 * Body: (usar solo los campos aplicables según el tipo de pregunta)
 * 
 * EJEMPLO 1 - RESPUESTA DE TEXTO:
 * {
 *   "codRespEnc": 10,
 *   "codPre": 25,
 *   "textoResp": "Mi respuesta en texto libre..."
 * }
 * 
 * EJEMPLO 2 - RESPUESTA DE OPCIÓN MÚLTIPLE:
 * {
 *   "codRespEnc": 10,
 *   "codPre": 26,
 *   "codOpcResp": 123        // ID de la opción seleccionada
 * }
 * 
 * EJEMPLO 3 - RESPUESTA DE ESCALA (1-5):
 * {
 *   "codRespEnc": 10,
 *   "codPre": 27,
 *   "valorResp": 4           // Valor seleccionado en la escala
 * }
 * 
 * EJEMPLO 4 - RESPUESTA DE CATÁLOGO:
 * {
 *   "codRespEnc": 10,
 *   "codPre": 28,
 *   "codDetCat": 456         // ID del detalle del catálogo
 * }
 * 
 * EJEMPLO 5 - RESPUESTA DE RANKING:
 * {
 *   "codRespEnc": 10,
 *   "codPre": 29,
 *   "posicionRank": 2        // Posición en el ranking
 * }
 * 
 * CAMPOS:
 * 
 * - codRespEnc (Long, OBLIGATORIO)
 *   ID de la sesión de respuesta de encuesta
 *   Obtenido de: POST /api/respuestas-encuesta/iniciar
 * 
 * - codPre (Long, OBLIGATORIO)
 *   ID de la pregunta que se está respondiendo
 *   Obtenido de: GET /api/preguntas?codEnc={id}
 * 
 * - textoResp (String, OPCIONAL)
 *   Respuesta libre en texto (sin límite)
 *   Usar para: Preguntas de tipo TEXTO
 *   Ejemplo: "Mi respuesta abierta..."
 * 
 * - codOpcResp (Long, OPCIONAL)
 *   ID de la opción seleccionada
 *   Usar para: Preguntas de tipo OPCION_MULTIPLE, MULTIPLES_OPCIONES
 *   Obtenido de: GET /api/opciones-respuesta?codPre={id}
 * 
 * - valorResp (Integer, OPCIONAL)
 *   Valor numérico de la respuesta
 *   Usar para: Preguntas de tipo ESCALA, RANGO
 *   Rango: Depende de la pregunta (ej. 1-5, 1-10)
 * 
 * - codDetCat (Long, OPCIONAL)
 *   ID del detalle del catálogo seleccionado
 *   Usar para: Preguntas de tipo CATALOGO
 *   Obtenido de: GET /api/detalles-catalogo?codCat={id}
 * 
 * - posicionRank (Integer, OPCIONAL)
 *   Posición o ranking asignado
 *   Usar para: Preguntas de tipo RANKING
 *   Ejemplo: Usuario ordena elementos: 1er lugar, 2do lugar, etc.
 * 
 * NOTAS IMPORTANTES:
 * - SOLO incluir los campos que correspondan al tipo de pregunta
 * - codRespEnc y codPre son SIEMPRE obligatorios
 * - Al menos uno de (textoResp, codOpcResp, valorResp, codDetCat, posicionRank) debe tener valor
 * - Enviar múltiples POST para guardar respuestas de múltiples preguntas
 * - Después de todas las respuestas, finalizar con: PUT /api/respuestas-encuesta/{id}/finalizar
 * 
 * FLUJO COMPLETO EN FRONTEND:
 * 1. POST /api/respuestas-encuesta/iniciar → obtener codRespEnc
 * 2. Iterar sobre preguntas: GET /api/preguntas?codEnc={id}
 * 3. Para cada pregunta:
 *    - Si tipo TEXTO → enviar textoResp
 *    - Si tipo OPCION_MULTIPLE → enviar codOpcResp
 *    - Si tipo ESCALA → enviar valorResp
 * 4. POST /api/respuestas (múltiples veces)
 * 5. PUT /api/respuestas-encuesta/{codRespEnc}/finalizar
 */
public class RespuestaRequest {
    private Long codRespEnc;
    private Long codPre;
    private Long codDetCat;
    private Long codOpcResp;
    private String textoResp;
    private Integer valorResp;
    private Integer posicionRank;

    public Long getCodRespEnc() {
        return codRespEnc;
    }

    public void setCodRespEnc(Long codRespEnc) {
        this.codRespEnc = codRespEnc;
    }

    public Long getCodPre() {
        return codPre;
    }

    public void setCodPre(Long codPre) {
        this.codPre = codPre;
    }

    public Long getCodDetCat() {
        return codDetCat;
    }

    public void setCodDetCat(Long codDetCat) {
        this.codDetCat = codDetCat;
    }

    public Long getCodOpcResp() {
        return codOpcResp;
    }

    public void setCodOpcResp(Long codOpcResp) {
        this.codOpcResp = codOpcResp;
    }

    public String getTextoResp() {
        return textoResp;
    }

    public void setTextoResp(String textoResp) {
        this.textoResp = textoResp;
    }

    public Integer getValorResp() {
        return valorResp;
    }

    public void setValorResp(Integer valorResp) {
        this.valorResp = valorResp;
    }

    public Integer getPosicionRank() {
        return posicionRank;
    }

    public void setPosicionRank(Integer posicionRank) {
        this.posicionRank = posicionRank;
    }
}
