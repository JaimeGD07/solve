package com.bad.solve.dto;

/**
 * DTO: PREGUNTA REQUEST
 * 
 * Objeto de transferencia de datos para crear o actualizar preguntas.
 * Contiene los datos mínimos necesarios para una pregunta.
 * 
 * USO EN FRONTEND:
 * 
 * CREAR PREGUNTA:
 * POST /api/preguntas
 * Body: {
 *   "codEnc": 1,              // ID de la encuesta a la que pertenece
 *   "codTipoPre": 2,           // ID del tipo de pregunta (TEXTO, OPCION_MULTIPLE, etc.)
 *   "codCat": 5,               // ID del catálogo de clasificación
 *   "enunciado": "¿Cuál es su edad?",  // Texto de la pregunta
 *   "obligatoria": 1            // 1 = obligatoria, 0 = opcional
 * }
 * Respuesta: Pregunta creada con codPre asignado
 * 
 * ACTUALIZAR PREGUNTA:
 * PUT /api/preguntas/{id}
 * Body: (mismo formato que CREATE)
 * 
 * CAMPOS:
 * 
 * - codEnc (Long, OBLIGATORIO)
 *   Identificador de la encuesta padre
 *   Obtener con: GET /api/encuestas
 *   Ejemplo: 1, 2, 3...
 * 
 * - codTipoPre (Long, OBLIGATORIO)
 *   Identificador del tipo de pregunta
 *   Obtener con: GET /api/tipos-pregunta
 *   Tipos comunes:
 *     1 = TEXTO (respuesta libre)
 *     2 = OPCION_MULTIPLE (seleccionar una)
 *     3 = MULTIPLES_OPCIONES (seleccionar varias)
 *     4 = ESCALA (1-5)
 *     5 = SI_NO
 * 
 * - codCat (Long, OBLIGATORIO)
 *   Identificador del catálogo para clasificación
 *   Obtener con: GET /api/catalogos
 *   Ejemplo: 1 (GENERO), 2 (ESTADO_CIVIL), etc.
 * 
 * - enunciado (String, OBLIGATORIO, máx 500 caracteres)
 *   Texto de la pregunta que ve el usuario
 *   Ejemplos:
 *     "¿Cuál es su nombre completo?"
 *     "¿Cuál es su edad?"
 *     "¿Qué tan satisfecho está con el servicio?"
 * 
 * - obligatoria (Integer, OPCIONAL, por defecto 1)
 *   1 = El usuario DEBE responder esta pregunta
 *   0 = El usuario PUEDE omitir esta pregunta
 * 
 * VALIDACIONES:
 * - codEnc: Debe existir en base de datos
 * - codTipoPre: Debe existir en base de datos
 * - codCat: Debe existir en base de datos
 * - enunciado: No puede estar vacío, máx 500 caracteres
 * - obligatoria: Solo acepta 0 o 1
 */
public class PreguntaRequest {
    private Long codEnc;
    private Long codTipoPre;
    private Long codCat;
    private String enunciado;
    private Integer obligatoria = 1;

    public Long getCodEnc() {
        return codEnc;
    }

    public void setCodEnc(Long codEnc) {
        this.codEnc = codEnc;
    }

    public Long getCodTipoPre() {
        return codTipoPre;
    }

    public void setCodTipoPre(Long codTipoPre) {
        this.codTipoPre = codTipoPre;
    }

    public Long getCodCat() {
        return codCat;
    }

    public void setCodCat(Long codCat) {
        this.codCat = codCat;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Integer getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Integer obligatoria) {
        this.obligatoria = obligatoria;
    }
}
