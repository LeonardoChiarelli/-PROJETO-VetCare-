package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.*;
import br.com.LeoChiarelli.api.domain.service.AbrigoService;
import br.com.LeoChiarelli.api.domain.service.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes")
public class TutorController {

    @Autowired
    private TutorService service;

    @Autowired
    private AbrigoService abrigoService;

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

    @GetMapping("/tutor/listar")
    public ResponseEntity<Page<ListaAbrigosDTO>> listarAbrigos(@PageableDefault(sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok(abrigoService.listar(pageable));
    }

    @GetMapping("/tutor/{idOuNome}/pets")
    public ResponseEntity<Page<ListaPetsDTO>> listarPetsDoAbrigo(@PathVariable String idOuNome, @PageableDefault(sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok(abrigoService.listarPets(idOuNome, pageable));
    }

    @GetMapping("/tutor/{idOuNome}")
    public ResponseEntity<DetalhesAbrigoDTO> detalharAbrigo(@PathVariable String idOuNome){
        return ResponseEntity.ok(abrigoService.detalhar(idOuNome));
    }

    @GetMapping("/tutor/{idOuNome}/{id}")
    public ResponseEntity<DetalhesPetDTO> detalharPet(@PathVariable String idOuNome, @PathVariable Long id){
        return ResponseEntity.ok(abrigoService.detalharPet(idOuNome, id));
    }

    @DeleteMapping("/tutor/{cpf}")
    @Transactional
    public ResponseEntity<String> deletarAdotante(@PathVariable String cpf){
        service.excluir(cpf);

        return ResponseEntity.ok("Tutor excluido");
    }

}
