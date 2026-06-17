# 📚 DOCUMENTACIÓN COMPLETA - PROYECTO SOLVE

## 📑 Índice de Documentación

Este proyecto incluye documentación completa para ayudar al frontend a integrarse con la API:

---

## 🎯 **1. Para Empezar Rápido**

### [README.md](./README.md)
- ✅ Descripción general del proyecto
- ✅ Instrucciones para ejecutar la aplicación
- ✅ Stack tecnológico
- ✅ Estructura del proyecto
- ✅ Primeros pasos

### [API_CHEATSHEET.md](./API_CHEATSHEET.md)  
- ✅ **Referencia rápida de todos los endpoints**
- ✅ Ejemplos de peticiones HTTP
- ✅ Filtros y búsquedas más comunes
- ✅ Flujos típicos
- ✅ Códigos de respuesta HTTP

---

## 📖 **2. Documentación Detallada**

### [FRONTEND_GUIDE.md](./FRONTEND_GUIDE.md)  
- ✅ **LA GUÍA PRINCIPAL PARA FRONTEND**
- ✅ Estructura completa de datos
- ✅ Todos los endpoints explicados
- ✅ Ejemplos detallados de peticiones
- ✅ Flujo completo de responder encuesta
- ✅ Recomendaciones y mejores prácticas
- ✅ Código JavaScript de inicio rápido

### [ARCHITECTURE.md](./ARCHITECTURE.md)
- ✅ **Diagramas y flujos visuales**
- ✅ Diagrama de entidades (ERD)
- ✅ Flujo de usuario respondiendo encuesta
- ✅ Flujo de admin creando encuesta
- ✅ Flujo de gestión de catálogos
- ✅ Relaciones entre tablas
- ✅ Conteo de llamadas API

---

## 💻 **3. Documentación en el Código**

Todos los archivos Java tienen comentarios detallados:

### Controllers (src/main/java/com/bad/solve/controller/)
```
✅ CatalogoController.java              - Gestión de catálogos
✅ UsuarioController.java               - Gestión de usuarios
✅ EncuestaController.java              - Gestión de encuestas
✅ PreguntaController.java              - Gestión de preguntas
✅ OpcionRespuestaController.java       - Gestión de opciones
✅ RespuestaController.java             - Gestión de respuestas
✅ RespuestaEncuestaController.java     - Gestión de sesiones
✅ RolController.java                   - Gestión de roles
✅ TipoPreguntaController.java          - Gestión de tipos
✅ Y más...
```

Cada controlador incluye:
- 📋 Descripción detallada
- 🔗 Todos los endpoints disponibles
- 📊 Parámetros y respuestas
- 💡 Ejemplos de uso
- ⚙️ Consideraciones especiales

### Entities (src/main/java/com/bad/solve/entity/)
```
✅ Usuario.java                - Estructura de usuario
✅ Encuesta.java              - Estructura de encuesta
✅ Pregunta.java              - Estructura de pregunta
✅ OpcionRespuesta.java       - Estructura de opciones
✅ Respuesta.java             - Estructura de respuesta
✅ RespuestaEncuesta.java     - Estructura de sesión
✅ Catalogo.java              - Estructura de catálogo
✅ Y más...
```

Cada entidad incluye:
- 📝 Descripción del propósito
- 🔑 Explicación de cada campo
- 🔗 Relaciones con otras entidades
- 📚 Ejemplos de uso
- ✔️ Validaciones y restricciones

### DTOs (src/main/java/com/bad/solve/dto/)
```
✅ PreguntaRequest.java              - Para crear preguntas
✅ RespuestaRequest.java             - Para guardar respuestas
✅ RespuestaEncuestaRequest.java     - Para iniciar encuesta
✅ AsignacionEncuestaRequest.java    - Para asignar encuesta
✅ Y más...
```

Cada DTO incluye:
- 📋 Explicación de cada campo
- 🎯 Casos de uso (cuándo usar)
- 📋 Ejemplos de petición
- ✔️ Validaciones obligatorias

---

## 🚀 Guía de Uso Recomendada

### Primer contacto:
1. Leer [README.md](./README.md) - Entender el proyecto
2. Leer [FRONTEND_GUIDE.md](./FRONTEND_GUIDE.md) - Entender la API
3. Revisar [API_CHEATSHEET.md](./API_CHEATSHEET.md) - Referencia rápida

### Durante desarrollo:
1. Usar [API_CHEATSHEET.md](./API_CHEATSHEET.md) para buscar endpoints
2. Consultar [FRONTEND_GUIDE.md](./FRONTEND_GUIDE.md) para detalles
3. Revisar comentarios en Controllers para opciones

### Para entender flujos:
1. Leer [ARCHITECTURE.md](./ARCHITECTURE.md) - Ver diagramas
2. Revisar FRONTEND_GUIDE.md sección "Flujo de encuesta"

### Para debugging:
1. Revisar comentarios en Entity/Controller específico
2. Consultar [API_CHEATSHEET.md](./API_CHEATSHEET.md) - Códigos HTTP

---

## 📊 Resumen Rápido de Datos

### USUARIO
```javascript
{
  codUsu: Long,
  primNom: String,
  email: String (único),
  passHash: String (WRITE_ONLY),
  estado: "ACTIVO" | "INACTIVO"
}
```

### ENCUESTA
```javascript
{
  codEnc: Long,
  titulo: String,
  descripcion: String,
  numPreguntas: Integer (automático)
}
```

### PREGUNTA
```javascript
{
  codPre: Long,
  codEnc: Long (obligatorio),
  codTipoPre: Long (obligatorio),
  enunciado: String,
  obligatoria: 1 | 0
}
```

### RESPUESTA ENCUESTA
```javascript
{
  codRespEnc: Long,
  codEnc: Long,
  codUsu: Long,
  estado: "EN_PROCESO" | "FINALIZADO" | "ANULADO",
  fechInicio: DateTime,
  fechFin: DateTime
}
```

### RESPUESTA (Individual)
```javascript
{
  codResp: Long,
  codRespEnc: Long,
  codPre: Long,
  textoResp: String,           // Para texto
  codOpcResp: Long,             // Para opción múltiple
  valorResp: Integer            // Para escala
}
```

---

## 🔗 Endpoints Más Importantes

| Operación | Método | Endpoint | Notas |
|-----------|--------|----------|-------|
| Ver encuestas asignadas | GET | `/api/asignaciones-encuesta?codUsu={id}` | ⭐ Inicio usuario |
| Iniciar encuesta | POST | `/api/respuestas-encuesta/iniciar` | ⭐ Guarda codRespEnc |
| Obtener preguntas | GET | `/api/preguntas?codEnc={id}` | ⭐ Para cada encuesta |
| Obtener opciones | GET | `/api/opciones-respuesta?codPre={id}` | ⭐ Para preguntas |
| Guardar respuesta | POST | `/api/respuestas` | ⭐ Múltiples veces |
| Finalizar | PUT | `/api/respuestas-encuesta/{id}/finalizar` | ⭐ Al terminar |

---

## ✨ Características Documentadas

### ✅ Autenticación
- Implementada en SecurityConfig.java
- Requerida en todos los endpoints

### ✅ CORS
- Habilitado automáticamente
- Permite peticiones desde cualquier dominio

### ✅ Validación
- En controllers (spring-boot-starter-validation)
- Documentada en DTOs

### ✅ Manejo de Errores
- Excepciones personalizadas
- Códigos HTTP estándar

### ✅ Seguridad
- Contraseñas como hash (WRITE_ONLY)
- Emails únicos
- Control de intentos de login

---

## 🎯 Casos de Uso Documentados

### 1. Usuario Responde Encuesta
```
Ver encuestas → Seleccionar → Iniciar → Responder preguntas → Guardar respuestas → Finalizar
```
📍 Documentado en: FRONTEND_GUIDE.md + ARCHITECTURE.md

### 2. Admin Crea Encuesta
```
Crear → Agregar preguntas → Agregar opciones → Asignar a usuarios
```
📍 Documentado en: ARCHITECTURE.md

### 3. Gestionar Catálogos
```
Crear catálogo → Agregar detalles → Usar en preguntas
```
📍 Documentado en: ARCHITECTURE.md

---

## 💡 Consejos Importantes

1. **Guarda en caché**: codEnc, codTipoPre, codCat (no cambian)
2. **Valida en frontend**: Antes de enviar al servidor
3. **Auto-save**: Guarda respuestas periódicamente
4. **Confirma**: Pregunta antes de finalizar encuesta
5. **Mejora UX**: Muestra indicadores de carga
6. **Maneja errores**: Muestra mensajes amigables

---

## 📱 Recomendaciones de Interfaz

```
FLUJO DE USUARIO:
┌─────────────────┐
│  Login/Sesión   │
└────────┬────────┘
         │
┌────────▼────────┐
│ Lista Encuestas │ ◄─── GET /api/asignaciones-encuesta?codUsu={id}
│ Asignadas       │
└────────┬────────┘
         │
┌────────▼────────┐
│ Seleccionar     │
│ Encuesta        │
└────────┬────────┘
         │
┌────────▼────────────────┐
│ POST /api/respuestas-   │
│ encuesta/iniciar        │ ◄─── Inicio sesión
└────────┬────────────────┘
         │
┌────────▼─────────────┐
│ Mostrar Preguntas    │ ◄─── GET /api/preguntas?codEnc={id}
│ y Opciones           │     GET /api/opciones-respuesta?codPre={id}
│ (formulario dinámico) │
└────────┬─────────────┘
         │
┌────────▼──────────────────┐
│ Usuario Responde          │
│ (inputs, checkboxes, etc) │
└────────┬──────────────────┘
         │
┌────────▼────────────────┐
│ POST /api/respuestas    │ ◄─── Auto-save periódico
│ (guardar cada respuesta)│     Una por una
└────────┬────────────────┘
         │
┌────────▼──────────────────┐
│ PUT /api/respuestas-     │ ◄─── Al hacer clic "Enviar"
│ encuesta/{id}/finalizar  │
└────────┬──────────────────┘
         │
┌────────▼───────────┐
│ ✅ Confirmación    │
│ Encuesta Enviada   │
└────────────────────┘
```

---

## 🔍 Solución de Problemas

| Problema | Solución |
|----------|----------|
| Endpoint no encontrado | Revisar [API_CHEATSHEET.md](./API_CHEATSHEET.md) |
| Error 400 | Revisar formato de JSON y DTOs |
| Error 404 | Verificar que IDs existen |
| Error 401 | Verificar autenticación |
| CORS Error | CORS está habilitado, revisar headers |

---

## 📞 Recursos Adicionales

- **Javadoc**: Comentarios en cada archivo .java
- **Controller Docs**: Comentarios en @GetMapping, @PostMapping
- **Entity Docs**: Comentarios en clases @Entity
- **DTO Docs**: Comentarios explicando cada campo

---

## ✅ Checklist de Integración

```
PREPARACIÓN:
☑️ Leer README.md
☑️ Leer FRONTEND_GUIDE.md
☑️ Revisar ARCHITECTURE.md
☑️ Ejecutar la aplicación

DESARROLLO:
☑️ Implementar login/sesión
☑️ Listar encuestas asignadas
☑️ Crear interfaz dinámico para preguntas
☑️ Guardar respuestas
☑️ Finalizar encuesta
☑️ Mostrar confirmación

TESTING:
☑️ Probar con usuario real
☑️ Probar con diferentes tipos de preguntas
☑️ Probar con múltiples encuestas
☑️ Probar manejo de errores
☑️ Revisar auto-save

OPTIMIZACIÓN:
☑️ Cachear datos estáticos
☑️ Auto-save de respuestas
☑️ Indicadores visuales de progreso
☑️ Validación en frontend
```

---

## 📝 Notas Finales

- Todos los archivos Java tienen comentarios exhaustivos
- Los DTOs explican qué enviar en cada petición
- Los Controllers documentan todos los endpoints
- Las Entities explican la estructura de datos
- Los flujos están diagramados en ARCHITECTURE.md

**¡Todo lo que necesitas para integrar el frontend está documentado!**

---

**Última actualización**: 17 de Junio 2024

🎉 **Documentación Completa** 🎉
