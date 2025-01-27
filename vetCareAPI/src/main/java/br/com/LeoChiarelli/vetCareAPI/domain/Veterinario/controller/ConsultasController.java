package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vet/consultas")
public class ConsultasController {

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(){}

    @PutMapping
    @Transactional
    public ResponseEntity update(){}

    @GetMapping
    public ResponseEntity read(){}

    @DeleteMapping
    public ResponseEntity delete(){}
}
