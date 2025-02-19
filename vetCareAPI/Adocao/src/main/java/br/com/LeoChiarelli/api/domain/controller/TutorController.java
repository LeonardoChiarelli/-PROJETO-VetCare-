package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.AtualizarInfoTutorDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarTutorDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesTutorDTO;
import br.com.LeoChiarelli.api.domain.service.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping("/tutores/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesTutorDTO> cadastrar(@RequestBody @Valid CadastrarTutorDTO dto, UriComponentsBuilder uriBuilder){
        var adotante = service.cadastrar(dto);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(adotante);
    }

    @PutMapping("/tutor/atualizar")
    @Transactional
    public ResponseEntity<DetalhesTutorDTO> atualizarInfo(@RequestBody @Valid AtualizarInfoTutorDTO dto){
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @GetMapping("/ong/{cpf}")
    public ResponseEntity<DetalhesTutorDTO> detalharAdotante(@PathVariable String cpf){
        return ResponseEntity.ok(service.detalhar(cpf));
    }

    @DeleteMapping("/tutor/{cpf}")
    @Transactional
    public ResponseEntity<String> deletarAdotante(@PathVariable String cpf){
        service.excluir(cpf);

        return ResponseEntity.ok("Tutor excluido");
    }


}
