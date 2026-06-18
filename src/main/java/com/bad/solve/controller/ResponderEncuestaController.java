package com.bad.solve.controller;

import com.bad.solve.dto.ResponderEncuestaRequest;
import com.bad.solve.service.ResponderEncuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/encuestas")
@CrossOrigin(origins = "http://localhost:4200")
public class ResponderEncuestaController {

    private final ResponderEncuestaService responderEncuestaService;

    public ResponderEncuestaController(ResponderEncuestaService responderEncuestaService) {
        this.responderEncuestaService = responderEncuestaService;
    }

    @PostMapping("/responder")
    public ResponseEntity<?> responderEncuesta(@RequestBody ResponderEncuestaRequest request) {
        Long codRespEnc = responderEncuestaService.responder(request);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Encuesta respondida correctamente",
                "codRespEnc", codRespEnc
        ));
    }
}