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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @Autowired
    private TutorService tutorService;

    @PostMapping("/abrigo/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesAbrigoDTO> cadastrarAbrigo(@RequestBody @Valid CadastrarAbrigoDTO dto, UriComponentsBuilder uriBuilder){
        var abrigo = service.cadastrar(dto);

        var uri = uriBuilder.path("/{id}").buildAndExpand(abrigo.id()).toUri();

        return ResponseEntity.created(uri).body(abrigo);
    }

    @PutMapping("/ong/atualizar")
    @Transactional
    public ResponseEntity<DetalhesAbrigoDTO> atualizarInfo(@RequestBody @Valid AtualizarAbrigoDTO dto){
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @GetMapping("/ong/{idOuNome}")
    public ResponseEntity<DetalhesAbrigoDTO> detalharAbrigo(@PathVariable String idOuNome){
        return ResponseEntity.ok(service.detalhar(idOuNome));
    }

    @PostMapping("/ong/pets/{idOuNome}")
    @Transactional
    public ResponseEntity<DetalhesPetDTO> cadastrarPetNoAbrigo(@RequestBody @Valid CadastrarPetDTO dto, @PathVariable String idOuNome, UriComponentsBuilder uriBuilder){
        var pet = service.cadastrarPet(dto, idOuNome);

        var uri = uriBuilder.path("/{idOuNome}/{id}").buildAndExpand(idOuNome, pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPetDTO(pet));
    }

    @GetMapping("/ong/{idOuNome}/pets")
    public ResponseEntity<Page<ListaPetsDTO>> listarPetsDoAbrigo(@PathVariable String idOuNome, @PageableDefault(sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok(service.listarPets(idOuNome, pageable));
    }

    @GetMapping("/ong/{idOuNome}/{id}")
    public ResponseEntity<DetalhesPetDTO> detalharPet(@PathVariable String idOuNome, @PathVariable Long id){
        return ResponseEntity.ok(service.detalharPet(idOuNome, id));
    }

    @GetMapping("/ong/{cpf}")
    public ResponseEntity<DetalhesTutorDTO> detalharAdotante(@PathVariable String cpf){
        return ResponseEntity.ok(tutorService.detalhar(cpf));
    }

    @PostMapping("/debug")
    public ResponseEntity<String> debugUserPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Usuário autenticado: " + authentication.getName());
        System.out.println("Roles: " + authentication.getAuthorities());

        return ResponseEntity.ok("Check logs for user roles.");
    }

}
