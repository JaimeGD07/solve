# GUÍA DE INTEGRACIÓN FRONTEND - API Solve

## 📋 Descripción General

Esta es una API REST para gestionar encuestas y respuestas de usuarios. Incluye autenticación, gestión de usuarios, creación de encuestas, preguntas y respuestas.

**URL Base**: `http://localhost:8080/api`

**Formato**: JSON

**Autenticación**: Requerida en todos los endpoints (excepto login)

---

## 🔑 Estructura de Datos Principales

### 1. USUARIOS
Representa los usuarios del sistema que responden encuestas.

**Campos principales**:
- `codUsu` (Long): ID del usuario
- `primNom` (String): Primer nombre
- `primApell` (String): Primer apellido
- `email` (String): Correo (único)
- `fechNac` (LocalDate): Fecha de nacimiento (YYYY-MM-DD)
- `pais` (String): País
- `ciudad` (String): Ciudad
- `passHash` (String): Contraseña (WRITE_ONLY - no se devuelve en GET)
- `estado` (String): ACTIVO, INACTIVO, SUSPENDIDO, BLOQUEADO
- `intentos` (Integer): Contador de intentos fallidos de login

**Endpoints**:
```
GET    /api/usuarios              # Listar todos
GET    /api/usuarios/{id}         # Obtener uno
POST   /api/usuarios              # Crear
PUT    /api/usuarios/{id}         # Actualizar
DELETE /api/usuarios/{id}         # Eliminar
```

---

### 2. ENCUESTAS
Agrupan preguntas relacionadas.

**Campos principales**:
- `codEnc` (Long): ID de la encuesta
- `titulo` (String): Título (máx 100 caracteres)
- `descripcion` (String): Descripción detallada
- `numPreguntas` (Integer): Número de preguntas (automático)
- `fechCrea` (LocalDateTime): Fecha de creación (automática)

**Endpoints**:
```
GET    /api/encuestas              # Listar todas
GET    /api/encuestas/{id}         # Obtener una
POST   /api/encuestas              # Crear
PUT    /api/encuestas/{id}         # Actualizar
DELETE /api/encuestas/{id}         # Eliminar
```

**Ejemplo de creación**:
```json
{
  "titulo": "Encuesta de Satisfacción",
  "descripcion": "Evalúe nuestro servicio"
}
```

---

### 3. PREGUNTAS
Preguntas individuales dentro de una encuesta.

**Campos principales**:
- `codPre` (Long): ID de la pregunta
- `codEnc` (Long): ID de la encuesta (obligatorio)
- `codTipoPre` (Long): ID del tipo de pregunta (obligatorio)
- `codCat` (Long): ID del catálogo (obligatorio)
- `enunciado` (String): Texto de la pregunta (máx 500 caracteres)
- `obligatoria` (Integer): 1 = obligatoria, 0 = opcional

**Tipos de pregunta**:
- TEXTO: Respuesta libre
- OPCION_MULTIPLE: Seleccionar una opción
- MULTIPLES_OPCIONES: Seleccionar varias
- ESCALA: Respuesta en escala (1-5, 1-10)
- SI_NO: Booleano
- FECHA: Selección de fecha
- ARCHIVO: Subida de archivo

**Endpoints**:
```
GET    /api/preguntas              # Listar todas
GET    /api/preguntas?codEnc={id}  # Listar por encuesta ⭐
GET    /api/preguntas/{id}         # Obtener una
POST   /api/preguntas              # Crear
PUT    /api/preguntas/{id}         # Actualizar
DELETE /api/preguntas/{id}         # Eliminar
```

**Ejemplo de creación**:
```json
{
  "codEnc": 1,
  "codTipoPre": 2,
  "codCat": 5,
  "enunciado": "¿Cuál es su edad?",
  "obligatoria": 1
}
```

**⭐ USO RECOMENDADO EN FRONTEND**:
```
1. GET /api/encuestas/{id} → obtener encuesta
2. GET /api/preguntas?codEnc={id} → obtener preguntas
3. Para cada pregunta, obtener opciones con siguiente endpoint
```

---

### 4. OPCIONES DE RESPUESTA
Alternativas para preguntas de opción múltiple.

**Campos principales**:
- `codOpcResp` (Long): ID de la opción
- `codPre` (Long): ID de la pregunta (obligatorio)
- `opcion` (String): Texto de la opción (máx 200 caracteres)
- `valor` (Integer): Valor numérico (opcional)
- `valorMin` (Integer): Valor mínimo para escalas
- `valorMax` (Integer): Valor máximo para escalas
- `orden` (Integer): Número de orden de presentación

**Endpoints**:
```
GET    /api/opciones-respuesta              # Listar todas
GET    /api/opciones-respuesta?codPre={id}  # Listar por pregunta ⭐
GET    /api/opciones-respuesta/{id}         # Obtener una
POST   /api/opciones-respuesta              # Crear
PUT    /api/opciones-respuesta/{id}         # Actualizar
DELETE /api/opciones-respuesta/{id}         # Eliminar
```

**⭐ USO EN FRONTEND**:
```
1. Obtener opciones: GET /api/opciones-respuesta?codPre={id}
2. Mostrar en SELECT, RADIO BUTTON o CHECKBOX
3. Usuario selecciona opción
4. Enviar respuesta con el ID de la opción seleccionada
```

---

### 5. CATÁLOGOS Y DETALLES
Colecciones de valores estándar reutilizables.

**Catálogo - Campos principales**:
- `codCat` (Long): ID del catálogo
- `nombre` (String): Nombre único (máx 50 caracteres)

**Ejemplos**: GENERO, ESTADO_CIVIL, NIVEL_EDUCACION

**DetalleCatalogo - Campos principales**:
- `codDetCat` (Long): ID del detalle
- `codCat` (Long): ID del catálogo padre
- `etiqueta` (String): Texto mostrado
- `valor` (Integer): Valor numérico
- `orden` (Integer): Número de orden

**Endpoints**:
```
GET    /api/catalogos                          # Listar catálogos
GET    /api/catalogos/{id}                     # Obtener catálogo
GET    /api/detalles-catalogo                  # Listar detalles
GET    /api/detalles-catalogo?codCat={id}      # Listar por catálogo ⭐
POST   /api/catalogos                          # Crear catálogo
POST   /api/detalles-catalogo                  # Crear detalle
```

**Ejemplo de uso en frontend**:
```
1. GET /api/catalogos → obtener catálogos disponibles
2. GET /api/detalles-catalogo?codCat={id} → obtener opciones
3. Mostrar en SELECT, RADIO BUTTON o DROPDOWN
```

---

## 📝 FLUJO DE ENCUESTA COMPLETO

### Usuario respondiendo encuesta:

```
1. OBTENER ENCUESTAS ASIGNADAS
   GET /api/asignaciones-encuesta?codUsu={id}
   ↓
2. USUARIO SELECCIONA UNA ENCUESTA
   Obtiene el codEnc de la lista
   ↓
3. INICIAR RESPUESTA DE ENCUESTA
   POST /api/respuestas-encuesta/iniciar
   Body: { "codEnc": 5, "codUsu": 12 }
   Respuesta: { "codRespEnc": 100, "estado": "EN_PROCESO", ... }
   ↓
4. OBTENER PREGUNTAS
   GET /api/preguntas?codEnc=5
   ↓
5. PARA CADA PREGUNTA:
   a) Si tipo = OPCION_MULTIPLE
      GET /api/opciones-respuesta?codPre={id}
      Usuario selecciona una opción
   
   b) Si tipo = TEXTO
      Usuario escribe respuesta libre
   
   c) Si tipo = ESCALA
      Usuario selecciona valor numérico
   ↓
6. GUARDAR CADA RESPUESTA
   POST /api/respuestas
   Body: { "codRespEnc": 100, "codPre": 25, "textoResp": "..." }
   O
   Body: { "codRespEnc": 100, "codPre": 26, "codOpcResp": 123 }
   O
   Body: { "codRespEnc": 100, "codPre": 27, "valorResp": 4 }
   ↓
7. FINALIZAR ENCUESTA
   PUT /api/respuestas-encuesta/100/finalizar
   Respuesta: { "codRespEnc": 100, "estado": "FINALIZADO", "fechFin": "..." }
```

---

## 🎯 ENDPOITS MÁS IMPORTANTES PARA FRONTEND

### Gestión de Usuarios
```
GET    /api/usuarios                # Listar usuarios
GET    /api/usuarios/{id}           # Ver perfil
POST   /api/usuarios                # Registrase
PUT    /api/usuarios/{id}           # Actualizar perfil
```

### Encuestas
```
GET    /api/encuestas               # Listar encuestas
GET    /api/encuestas/{id}          # Ver detalle de encuesta
GET    /api/preguntas?codEnc={id}   # Ver preguntas de encuesta ⭐
```

### Responder Encuesta
```
GET    /api/asignaciones-encuesta?codUsu={id}     # Ver encuestas asignadas ⭐
POST   /api/respuestas-encuesta/iniciar           # Empezar a responder ⭐
POST   /api/respuestas                            # Guardar respuesta ⭐
PUT    /api/respuestas-encuesta/{id}/finalizar    # Terminar encuesta ⭐
```

### Opciones
```
GET    /api/opciones-respuesta?codPre={id}       # Ver opciones de pregunta ⭐
GET    /api/detalles-catalogo?codCat={id}        # Ver valores de catálogo ⭐
```

---

## 📤 EJEMPLOS DE PETICIONES COMUNES

### 1. Crear Usuario
```bash
POST /api/usuarios
Content-Type: application/json

{
  "primNom": "Juan",
  "primApell": "Pérez",
  "email": "juan@example.com",
  "passHash": "miPassword123",
  "fechNac": "1990-01-15",
  "pais": "Colombia",
  "ciudad": "Bogotá"
}
```

### 2. Obtener Encuesta con sus Preguntas
```bash
GET /api/encuestas/5
GET /api/preguntas?codEnc=5
GET /api/opciones-respuesta?codPre=25
```

### 3. Responder Encuesta
```bash
# Paso 1: Iniciar
POST /api/respuestas-encuesta/iniciar
{ "codEnc": 5, "codUsu": 12 }

# Paso 2: Guardar respuesta de texto
POST /api/respuestas
{ "codRespEnc": 100, "codPre": 25, "textoResp": "Mi respuesta" }

# Paso 3: Guardar respuesta de opción múltiple
POST /api/respuestas
{ "codRespEnc": 100, "codPre": 26, "codOpcResp": 123 }

# Paso 4: Guardar respuesta de escala
POST /api/respuestas
{ "codRespEnc": 100, "codPre": 27, "valorResp": 4 }

# Paso 5: Finalizar
PUT /api/respuestas-encuesta/100/finalizar
```

---

## ⚙️ CONFIGURACIÓN

### CORS
✅ CORS está habilitado - Puedes hacer peticiones desde cualquier dominio

### Seguridad
- Las contraseñas (`passHash`) NO se devuelven en GET
- Todos los endpoints requieren autenticación (excepto login)
- El email es único por usuario

### Validaciones Comunes
- Nombres: máximo 50 caracteres
- Email: máximo 254 caracteres, debe ser único
- Enunciados: máximo 500 caracteres
- `obligatoria`: solo acepta 1 o 0
- Fechas: formato ISO (YYYY-MM-DD)

---

## 🔒 SEGURIDAD

### Campos sensibles
- `passHash`: WRITE_ONLY (no se devuelve en GET)
- Solo se puede establecer en POST y PUT
- El email es único para prevenir duplicados

### Control de intentos
- Campo `intentos` en usuario
- Se incrementa con cada login fallido
- Se resetea con login exitoso

---

## 📊 CÓDIGOS DE RESPUESTA

| Código | Significado |
|--------|------------|
| 200 | OK - Solicitud exitosa |
| 201 | Created - Recurso creado |
| 204 | No Content - Solicitud exitosa sin contenido |
| 400 | Bad Request - Datos inválidos |
| 401 | Unauthorized - Autenticación requerida |
| 403 | Forbidden - Permiso denegado |
| 404 | Not Found - Recurso no existe |
| 409 | Conflict - Conflicto (ej. duplicado) |
| 500 | Internal Server Error - Error del servidor |

---

## 💡 RECOMENDACIONES PARA FRONTEND

1. **Cachear datos estáticos**: Catálogos, tipos de pregunta, roles
2. **Validación en cliente**: Antes de enviar al servidor
3. **Manejo de errores**: Mostrar mensajes amigables al usuario
4. **Paginación**: Para listas grandes (a futuro)
5. **Indicador de progreso**: Mostrar cuando se guarda una respuesta
6. **Validar que preguntas obligatorias tengan respuesta**
7. **Confirmación antes de finalizar encuesta**
8. **Guardar respuestas periódicamente** (auto-save)

---

## 🚀 INICIO RÁPIDO

```javascript
// 1. Obtener usuario actual
const usuario = await fetch('/api/usuarios/12').then(r => r.json());

// 2. Ver encuestas asignadas
const encuestas = await fetch('/api/asignaciones-encuesta?codUsu=12').then(r => r.json());

// 3. Seleccionar una encuesta
const encuesta = encuestas[0];

// 4. Iniciar respuesta
const respuesta = await fetch('/api/respuestas-encuesta/iniciar', {
  method: 'POST',
  body: JSON.stringify({ codEnc: encuesta.codEnc, codUsu: usuario.codUsu })
}).then(r => r.json());

// 5. Obtener preguntas
const preguntas = await fetch(`/api/preguntas?codEnc=${encuesta.codEnc}`).then(r => r.json());

// 6. Para cada pregunta, mostrar opciones y capturar respuesta
for (let pregunta of preguntas) {
  const opciones = await fetch(`/api/opciones-respuesta?codPre=${pregunta.codPre}`).then(r => r.json());
  // Mostrar en UI
}

// 7. Guardar respuesta
await fetch('/api/respuestas', {
  method: 'POST',
  body: JSON.stringify({
    codRespEnc: respuesta.codRespEnc,
    codPre: pregunta.codPre,
    textoResp: "respuesta del usuario"
  })
});

// 8. Finalizar
await fetch(`/api/respuestas-encuesta/${respuesta.codRespEnc}/finalizar`, { method: 'PUT' });
```

---

**Última actualización**: 17 de Junio 2024

Para más información, revisar los comentarios en el código fuente.
