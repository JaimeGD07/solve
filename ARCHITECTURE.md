# 🎯 Flujos y Arquitectura - Solve

## Estructura General del Sistema

```
┌─────────────────────────────────────────────────────────────┐
│                        FRONTEND (Angular/Vue/React)          │
└────────────────────────┬────────────────────────────────────┘
                         │ JSON / HTTP
┌────────────────────────▼────────────────────────────────────┐
│                    API REST - SOLVE                           │
│  Base URL: http://localhost:8080/api                          │
│  ✅ CORS Habilitado  🔒 Autenticación Requerida              │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│                    BASE DE DATOS                              │
│  MySQL / PostgreSQL / H2                                      │
└────────────────────────────────────────────────────────────┘
```

---

## 📊 Estructura de Datos

```
┌──────────────┐
│   USUARIO    │
├──────────────┤
│ codUsu (PK)  │
│ primNom      │
│ primApell    │
│ email        │
│ passHash     │
│ estado       │
└──────────────┘
       │
       │ 1:N
       │
┌──────────────┐         ┌─────────────────┐
│   ENCUESTA   │◄────────│  ASIGNACION     │
├──────────────┤         │ ENCUESTA        │
│ codEnc (PK)  │  1:N    ├─────────────────┤
│ titulo       │────────►│ codEnc (FK)     │
│ descripcion  │         │ codUsu (FK)     │
└──────────────┘         └─────────────────┘
       │
       │ 1:N
       │
    ┌──────────────┐
    │  PREGUNTA    │
    ├──────────────┤
    │ codPre (PK)  │
    │ codEnc (FK)  │
    │ enunciado    │
    │ obligatoria  │
    └──────────────┘
       │
       │ 1:N
       │
    ┌────────────────────┐
    │ OPCION_RESPUESTA   │
    ├────────────────────┤
    │ codOpcResp (PK)    │
    │ codPre (FK)        │
    │ opcion             │
    │ valor              │
    └────────────────────┘

┌──────────────────────┐      ┌─────────────────┐
│ RESPUESTA_ENCUESTA   │◄─────│   RESPUESTA     │
├──────────────────────┤ 1:N  ├─────────────────┤
│ codRespEnc (PK)      │      │ codResp (PK)    │
│ codEnc (FK)          │      │ codRespEnc (FK) │
│ codUsu (FK)          │      │ codPre (FK)     │
│ estado               │      │ textoResp       │
│ fechInicio           │      │ codOpcResp (FK) │
│ fechFin              │      │ valorResp       │
└──────────────────────┘      └─────────────────┘

┌──────────────┐
│   CATALOGO   │
├──────────────┤
│ codCat (PK)  │
│ nombre       │
└──────────────┘
       │
       │ 1:N
       │
┌──────────────────────┐
│ DETALLE_CATALOGO     │
├──────────────────────┤
│ codDetCat (PK)       │
│ codCat (FK)          │
│ etiqueta             │
│ valor                │
└──────────────────────┘
```

---

## 🔄 FLUJO 1: USUARIO RESPONDE ENCUESTA

```
┌─────────────────────────────────────────────────────────────┐
│ USUARIO INICIA SESIÓN                                       │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Ver encuestas asignadas                                     │
│ GET /api/asignaciones-encuesta?codUsu={id}                │
│ Respuesta: Lista de encuestas disponibles                   │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Usuario selecciona una encuesta                             │
│ Obtiene: codEnc                                             │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ INICIAR respuesta de encuesta                               │
│ POST /api/respuestas-encuesta/iniciar                      │
│ Body: { "codEnc": 5, "codUsu": 12 }                        │
│ Retorna: codRespEnc (importante, guardar)                   │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Obtener preguntas de la encuesta                            │
│ GET /api/preguntas?codEnc={id}                             │
│ Respuesta: Array de Pregunta                                │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ PARA CADA PREGUNTA:                                         │
│                                                              │
│ ┌────────────────────────────────────────────────────────┐ │
│ │ Obtener tipo de pregunta (del objeto Pregunta)        │ │
│ └────────────────────────────────────────────────────────┘ │
│                           │                                 │
│                ┌──────────┼──────────┬────────────┐         │
│                ▼          ▼          ▼            ▼         │
│          ┌──────────┐ ┌───────────┐ ┌──────────┐ ┌──────┐  │
│          │ TEXTO    │ │ OPCION_   │ │ ESCALA   │ │OTROS │  │
│          │          │ │ MULTIPLE  │ │(1-5)     │ │TIPOS │  │
│          └──────────┘ └─────────┬─┘ └──────┬───┘ └──────┘  │
│                                │           │                │
│                                ▼           ▼                │
│                     ┌──────────────────────────────────┐    │
│                     │ GET /api/opciones-respuesta      │    │
│                     │ ?codPre={id}                     │    │
│                     │ Retorna: opciones para elegir   │    │
│                     └──────────────────────────────────┘    │
│                                │                            │
│                                ▼                            │
│                     ┌──────────────────────────────────┐    │
│                     │ Usuario selecciona opción        │    │
│                     │ (radio button, dropdown, etc)    │    │
│                     └──────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Guardar respuesta de pregunta                               │
│ POST /api/respuestas                                        │
│ Body: {                                                     │
│   "codRespEnc": 100,     // De paso 1                      │
│   "codPre": 25,          // De esta pregunta               │
│   "textoResp": "..."     // Si es texto                    │
│   O "codOpcResp": 123    // Si seleccionó opción          │
│   O "valorResp": 4       // Si es escala                   │
│ }                                                           │
└─────────────────────────────────────────────────────────────┘
                           │
           ┌───────────────┴───────────────┐
           │                               │
       ¿Más preguntas?                 No
           │ Sí
           ▼
      (volver a FOR)
                                   │
                                   ▼
┌─────────────────────────────────────────────────────────────┐
│ FINALIZAR encuesta                                          │
│ PUT /api/respuestas-encuesta/{codRespEnc}/finalizar       │
│ Retorna: RespuestaEncuesta con estado="FINALIZADO"         │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ ✅ ENCUESTA COMPLETADA                                      │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎨 FLUJO 2: ADMIN CREA ENCUESTA

```
┌─────────────────────────────────────────────────────────────┐
│ ADMIN INICIA SESIÓN                                         │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ CREAR ENCUESTA                                              │
│ POST /api/encuestas                                         │
│ Body: { "titulo": "...", "descripcion": "..." }            │
│ Retorna: codEnc (importante, guardar)                       │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ CREAR PREGUNTAS (múltiples)                                 │
│                                                              │
│ Para cada pregunta:                                         │
│ POST /api/preguntas                                         │
│ Body: {                                                     │
│   "codEnc": {del paso anterior},                           │
│   "codTipoPre": 2,     // Tipo: OPCION_MULTIPLE            │
│   "codCat": 5,         // Categoría                        │
│   "enunciado": "...",  // Pregunta                         │
│   "obligatoria": 1     // 1=obligatoria, 0=opcional       │
│ }                                                           │
│ Retorna: codPre (guardar para siguiente paso)              │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Si la pregunta es OPCION_MULTIPLE:                          │
│ CREAR OPCIONES DE RESPUESTA (múltiples)                     │
│                                                              │
│ Para cada opción:                                           │
│ POST /api/opciones-respuesta                                │
│ Body: {                                                     │
│   "codPre": {de pregunta},                                 │
│   "opcion": "Opción A",                                    │
│   "valor": 1,                                              │
│   "orden": 1                                               │
│ }                                                           │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Obtener lista de usuarios para asignar                      │
│ GET /api/usuarios                                           │
│ Respuesta: Array de usuarios                                │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ ASIGNAR ENCUESTA A USUARIOS (múltiples)                     │
│                                                              │
│ Para cada usuario a asignar:                                │
│ POST /api/asignaciones-encuesta                             │
│ Body: {                                                     │
│   "codEnc": {de la encuesta},                              │
│   "codUsu": {del usuario}                                  │
│ }                                                           │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ ✅ ENCUESTA CREADA Y ASIGNADA                               │
│ Los usuarios podrán verla en su lista de encuestas          │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ VER RESPUESTAS RECOPILADAS                                  │
│ GET /api/respuestas-encuesta?codEnc={id}                  │
│ Retorna: Todas las respuestas de esa encuesta              │
│                                                              │
│ Para ver respuestas individuales:                           │
│ GET /api/respuestas?codRespEnc={id}                       │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎯 FLUJO 3: GESTIÓN DE CATÁLOGOS

```
┌─────────────────────────────────────────────────────────────┐
│ Catálogos son colecciones de valores reutilizables          │
│ Ejemplo: GENERO, ESTADO_CIVIL, NIVEL_EDUCACION             │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ CREAR CATÁLOGO                                              │
│ POST /api/catalogos                                         │
│ Body: { "nombre": "GENERO" }                                │
│ Retorna: codCat                                             │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ AGREGAR DETALLES AL CATÁLOGO (múltiples)                    │
│                                                              │
│ Para cada valor:                                            │
│ POST /api/detalles-catalogo                                 │
│ Body: {                                                     │
│   "codCat": {del catálogo},                                │
│   "etiqueta": "Masculino",                                 │
│   "valor": 1,                                              │
│   "orden": 1                                               │
│ }                                                           │
└─────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│ Usar en preguntas:                                          │
│ POST /api/preguntas                                         │
│ Body: {                                                     │
│   "codEnc": 1,                                             │
│   "codTipoPre": 2,                                         │
│   "codCat": {del catálogo},  ← Aquí                        │
│   "enunciado": "¿Cuál es su género?",                     │
│   "obligatoria": 1                                         │
│ }                                                           │
└─────────────────────────────────────────────────────────────┘
```

---

## 📈 CONTEO DE LLAMADAS API

### Para responder UNA encuesta con 5 preguntas:
```
1. GET  /api/asignaciones-encuesta?codUsu=... (1 llamada)
2. POST /api/respuestas-encuesta/iniciar       (1 llamada)
3. GET  /api/preguntas?codEnc=...             (1 llamada)
4. GET  /api/opciones-respuesta?codPre=...    (5 llamadas, una por pregunta)
5. POST /api/respuestas                        (5 llamadas, una por respuesta)
6. PUT  /api/respuestas-encuesta/.../finalizar (1 llamada)

TOTAL: 14 llamadas API
```

### Para crear una encuesta con 5 preguntas y 4 opciones cada una:
```
1. POST /api/encuestas                    (1 llamada)
2. POST /api/preguntas                    (5 llamadas)
3. POST /api/opciones-respuesta           (20 llamadas, 4 por pregunta)
4. GET  /api/usuarios                     (1 llamada)
5. POST /api/asignaciones-encuesta        (10 llamadas, para 10 usuarios)

TOTAL: 37 llamadas API
```

---

## 🔐 Estados de Entidades

### Estado de Usuario
```
ACTIVO      → Puede usar el sistema
INACTIVO    → No puede acceder
SUSPENDIDO  → Suspendido temporalmente
BLOQUEADO   → Bloqueado por intentos fallidos
```

### Estado de RespuestaEncuesta
```
EN_PROCESO  → Usuario está respondiendo
FINALIZADO  → Usuario completó y envió
ANULADO     → Usuario canceló sin enviar
```

---

## 🔍 Relaciones Clave

```
Usuario (1) ──────→ (N) UsuarioEncuesta
Usuario (1) ──────→ (N) RespuestaEncuesta
Usuario (1) ──────→ (N) Respuesta (indirecta)

Encuesta (1) ──────→ (N) Pregunta
Encuesta (1) ──────→ (N) UsuarioEncuesta
Encuesta (1) ──────→ (N) RespuestaEncuesta

Pregunta (1) ──────→ (N) OpcionRespuesta
Pregunta (1) ──────→ (N) Respuesta

RespuestaEncuesta (1) ──────→ (N) Respuesta

Catalogo (1) ──────→ (N) DetalleCatalogo
Catalogo (1) ──────→ (N) Pregunta
```

---

## ✅ Checklist para Integración Frontend

```
□ Entender la estructura de datos (leer entity/)
□ Revisar endpoints en controllers/
□ Revisar DTOs (estructura de datos enviados/recibidos)
□ Implementar login y gestión de sesión
□ Mostrar lista de encuestas asignadas
□ Crear formulario dinámico basado en preguntas
□ Guardar respuestas según tipo de pregunta
□ Manejar errores HTTP (400, 404, 500)
□ Mostrar indicadores de progreso/carga
□ Validar campos obligatorios antes de enviar
□ Confirmar antes de finalizar encuesta
□ Mostrar mensajes de éxito/error
□ Considerar auto-save de respuestas
```

---

**Última actualización**: 17 de Junio 2024
