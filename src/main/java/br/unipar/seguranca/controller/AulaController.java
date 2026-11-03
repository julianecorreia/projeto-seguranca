package br.unipar.seguranca.controller;

import java.security.Principal;

import br.unipar.seguranca.service.AulaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AulaController {
    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping("/api/publico")
    public ResponseEntity<String> publico() {
        return ResponseEntity.ok(aulaService.publico());
    }

    @GetMapping("/api/privado")
    public ResponseEntity<String> privado(Principal usuario) {
        //return unauthorized
        return ResponseEntity.ok(aulaService.privado(usuario.getName()));
    }
}
