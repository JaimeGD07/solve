# API Cheat Sheet - Solve

**URL Base**: `http://localhost:8080/api`

---

## USUARIOS

```
📋 Listar usuarios
GET /api/usuarios

👤 Obtener usuario
GET /api/usuarios/{id}

➕ Crear usuario
POST /api/usuarios
{
  "primNom": "Juan",
  "primApell": "Pérez",
  "email": "juan@example.com",
  "passHash": "password123",
  "fechNac": "1990-01-15",
  "pais": "Colombia",
  "ciudad": "Bogotá"
}

✏️ Actualizar usuario
PUT /api/usuarios/{id}

🗑️ Eliminar usuario
DELETE /api/usuarios/{id}
```

---

## ENCUESTAS

```
📋 Listar encuestas
GET /api/encuestas

📄 Obtener encuesta
GET /api/encuestas/{id}

➕ Crear encuesta
POST /api/encuestas
{
  "titulo": "Encuesta de Satisfacción",
  "descripcion": "Descripción de la encuesta"
}

✏️ Actualizar encuesta
PUT /api/encuestas/{id}

🗑️ Eliminar encuesta
DELETE /api/encuestas/{id}
```

---

## PREGUNTAS ⭐

```
📋 Listar preguntas
GET /api/preguntas

⭐ Listar preguntas de encuesta (MÁS USADO)
GET /api/preguntas?codEnc={id}

❓ Obtener pregunta
GET /api/preguntas/{id}

➕ Crear pregunta
POST /api/preguntas
{
  "codEnc": 1,
  "codTipoPre": 2,
  "codCat": 5,
  "enunciado": "¿Cuál es su edad?",
  "obligatoria": 1
}

✏️ Actualizar pregunta
PUT /api/preguntas/{id}

🗑️ Eliminar pregunta
DELETE /api/preguntas/{id}
```

**Nota**: `codTipoPre` = tipo de pregunta (TEXTO, OPCION_MULTIPLE, ESCALA, etc.)

---

## OPCIONES DE RESPUESTA ⭐

```
📋 Listar opciones
GET /api/opciones-respuesta

⭐ Listar opciones de pregunta (MÁS USADO)
GET /api/opciones-respuesta?codPre={id}

🔘 Obtener opción
GET /api/opciones-respuesta/{id}

➕ Crear opción
POST /api/opciones-respuesta
{
  "codPre": 1,
  "opcion": "Sí",
  "valor": 1,
  "orden": 1
}

✏️ Actualizar opción
PUT /api/opciones-respuesta/{id}

🗑️ Eliminar opción
DELETE /api/opciones-respuesta/{id}
```

---

## CATÁLOGOS

```
📋 Listar catálogos
GET /api/catalogos

📚 Obtener catálogo
GET /api/catalogos/{id}

➕ Crear catálogo
POST /api/catalogos
{
  "nombre": "GENERO"
}

✏️ Actualizar catálogo
PUT /api/catalogos/{id}

🗑️ Eliminar catálogo
DELETE /api/catalogos/{id}
```

---

## DETALLES DE CATÁLOGO ⭐

```
📋 Listar detalles
GET /api/detalles-catalogo

⭐ Listar detalles de catálogo (MÁS USADO)
GET /api/detalles-catalogo?codCat={id}

🏷️ Obtener detalle
GET /api/detalles-catalogo/{id}

➕ Crear detalle
POST /api/detalles-catalogo
{
  "codCat": 1,
  "etiqueta": "Masculino",
  "valor": 1,
  "orden": 1
}

✏️ Actualizar detalle
PUT /api/detalles-catalogo/{id}

🗑️ Eliminar detalle
DELETE /api/detalles-catalogo/{id}
```

---

## ASIGNACIONES DE ENCUESTA ⭐

```
📋 Listar todas las asignaciones
GET /api/asignaciones-encuesta

👤 Encuestas asignadas a usuario (MÁS USADO)
GET /api/asignaciones-encuesta?codUsu={id}

📋 Usuarios asignados a encuesta
GET /api/asignaciones-encuesta?codEnc={id}

➕ Asignar encuesta a usuario
POST /api/asignaciones-encuesta
{
  "codEnc": 5,
  "codUsu": 12
}

🗑️ Desasignar encuesta
DELETE /api/asignaciones-encuesta?codEnc={id}&codUsu={id}
```

---

## RESPUESTAS DE ENCUESTA ⭐

```
📋 Listar respuestas
GET /api/respuestas-encuesta

👤 Respuestas de usuario (MÁS USADO)
GET /api/respuestas-encuesta?codUsu={id}

📋 Respuestas de encuesta
GET /api/respuestas-encuesta?codEnc={id}

🔍 Obtener respuesta
GET /api/respuestas-encuesta/{id}

⭐ INICIAR respuesta de encuesta (MÁS USADO)
POST /api/respuestas-encuesta/iniciar
{
  "codEnc": 5,
  "codUsu": 12
}
Retorna: codRespEnc para usar en siguientes peticiones

✏️ Finalizar respuesta
PUT /api/respuestas-encuesta/{id}/finalizar

❌ Anular respuesta
PUT /api/respuestas-encuesta/{id}/anular

🗑️ Eliminar respuesta
DELETE /api/respuestas-encuesta/{id}
```

---

## RESPUESTAS (INDIVIDUALES) ⭐

```
📋 Listar respuestas
GET /api/respuestas

❓ Respuestas de RespuestaEncuesta
GET /api/respuestas?codRespEnc={id}

🔍 Obtener respuesta
GET /api/respuestas/{id}

⭐ GUARDAR respuesta de pregunta (MÁS USADO)
POST /api/respuestas
{
  "codRespEnc": 100,
  "codPre": 25,
  "textoResp": "Mi respuesta de texto"
}
O
{
  "codRespEnc": 100,
  "codPre": 26,
  "codOpcResp": 123
}
O
{
  "codRespEnc": 100,
  "codPre": 27,
  "valorResp": 4
}

✏️ Actualizar respuesta
PUT /api/respuestas/{id}

🗑️ Eliminar respuesta
DELETE /api/respuestas/{id}
```

---

## ROLES

```
📋 Listar roles
GET /api/roles

🎭 Obtener rol
GET /api/roles/{id}

➕ Crear rol
POST /api/roles
{
  "nombre": "ADMIN",
  "descripcion": "Administrador del sistema"
}

✏️ Actualizar rol
PUT /api/roles/{id}

🗑️ Eliminar rol
DELETE /api/roles/{id}
```

---

## TIPOS DE PREGUNTA

```
📋 Listar tipos
GET /api/tipos-pregunta

🏷️ Obtener tipo
GET /api/tipos-pregunta/{id}

➕ Crear tipo
POST /api/tipos-pregunta
{
  "nombre": "TEXTO",
  "descripcion": "Respuesta libre en texto"
}

✏️ Actualizar tipo
PUT /api/tipos-pregunta/{id}

🗑️ Eliminar tipo
DELETE /api/tipos-pregunta/{id}
```

---

## 🎯 FLUJO TÍPICO: USUARIO RESPONDE ENCUESTA

```
1️⃣ Ver encuestas asignadas
   GET /api/asignaciones-encuesta?codUsu=12

2️⃣ Seleccionar encuesta (obtener codEnc)

3️⃣ Iniciar respuesta
   POST /api/respuestas-encuesta/iniciar
   { "codEnc": 5, "codUsu": 12 }
   ➜ Guardar codRespEnc

4️⃣ Obtener preguntas
   GET /api/preguntas?codEnc=5

5️⃣ Para cada pregunta:
   
   Si tipo OPCION_MULTIPLE:
     GET /api/opciones-respuesta?codPre=25
     Usuario selecciona opción
   
   Si tipo TEXTO:
     Usuario escribe respuesta
   
   Si tipo ESCALA:
     GET /api/opciones-respuesta?codPre=27
     Usuario selecciona valor

6️⃣ Guardar cada respuesta
   POST /api/respuestas
   { "codRespEnc": 100, "codPre": 25, "textoResp": "..." }

7️⃣ Finalizar encuesta
   PUT /api/respuestas-encuesta/100/finalizar
```

---

## 🎯 FLUJO TÍPICO: ADMIN CREA ENCUESTA

```
1️⃣ Crear encuesta
   POST /api/encuestas
   { "titulo": "Mi Encuesta", "descripcion": "..." }
   ➜ Guardar codEnc

2️⃣ Crear preguntas
   POST /api/preguntas (múltiples)
   { "codEnc": 1, "codTipoPre": 2, "codCat": 5, "enunciado": "...", "obligatoria": 1 }
   ➜ Guardar codPre de cada pregunta

3️⃣ Si es OPCION_MULTIPLE, crear opciones
   POST /api/opciones-respuesta (múltiples)
   { "codPre": 25, "opcion": "Sí", "valor": 1, "orden": 1 }

4️⃣ Asignar a usuarios
   POST /api/asignaciones-encuesta (múltiples)
   { "codEnc": 1, "codUsu": 12 }
   { "codEnc": 1, "codUsu": 13 }
   { "codEnc": 1, "codUsu": 14 }

5️⃣ Ver respuestas de usuarios
   GET /api/respuestas-encuesta?codEnc=1
```

---

## 📊 CÓDIGOS DE RESPUESTA HTTP

| Código | Significado | Acción |
|--------|-----------|--------|
| 200 | OK | Solicitud exitosa |
| 201 | Created | Recurso creado |
| 204 | No Content | Exitosa sin contenido |
| 400 | Bad Request | Datos inválidos |
| 401 | Unauthorized | Autenticación requerida |
| 403 | Forbidden | Permiso denegado |
| 404 | Not Found | Recurso no existe |
| 409 | Conflict | Duplicado o conflicto |
| 500 | Server Error | Error del servidor |

---

## 🔍 BÚSQUEDAS Y FILTROS MÁS COMUNES

```
⭐ Preguntas de encuesta
GET /api/preguntas?codEnc=5

⭐ Opciones de pregunta
GET /api/opciones-respuesta?codPre=25

⭐ Detalles de catálogo
GET /api/detalles-catalogo?codCat=1

⭐ Encuestas del usuario
GET /api/asignaciones-encuesta?codUsu=12

⭐ Respuestas de usuario
GET /api/respuestas-encuesta?codUsu=12

⭐ Respuestas de encuesta
GET /api/respuestas-encuesta?codEnc=5

⭐ Respuestas individuales de una sesión
GET /api/respuestas?codRespEnc=100
```

---

## ✨ CONSEJOS

1. **Guarda IDs en caché**: codEnc, codTipoPre, codCat (no cambian)
2. **Valida en frontend**: Antes de enviar al servidor
3. **Maneja errores**: Muestra mensajes amigables
4. **Auto-save de respuestas**: Guarda periódicamente
5. **Confirma antes de finalizar**: Pregunta si está seguro
6. **Valida campos obligatorios**: Las preguntas con obligatoria=1 deben tener respuesta
7. **Usa indicadores de carga**: Muestra feedback visual mientras se guardan respuestas

---

**Última actualización**: 17 de Junio 2024
