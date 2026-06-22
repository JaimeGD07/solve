package com.bad.solve.controller;

import com.bad.solve.dto.CrearEncuestaCompletaRequest;
import com.bad.solve.service.EncuestaCompletaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/encuestas")
@CrossOrigin(origins = "http://localhost:4200")
public class EncuestaCompletaController {

    private final EncuestaCompletaService encuestaCompletaService;

    public EncuestaCompletaController(EncuestaCompletaService encuestaCompletaService) {
        this.encuestaCompletaService = encuestaCompletaService;
    }

    @PostMapping("/completa")
    public ResponseEntity<Map<String, Object>> crearEncuestaCompleta(
            @RequestBody CrearEncuestaCompletaRequest request
    ) {
        Long codEnc = encuestaCompletaService.crearEncuestaCompleta(request);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Encuesta creada correctamente",
                "codEnc", codEnc
        ));
    }
}