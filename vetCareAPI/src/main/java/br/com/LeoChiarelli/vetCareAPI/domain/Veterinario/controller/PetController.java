package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.*;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IPetRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.PetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class PetController {

    @Autowired
    private PetService service;

    @Autowired
    private IPetRepository repository;

    @PostMapping({"/tutor/pets", "/admin/pets"})
    @Transactional
    public ResponseEntity<DetalhesPetCadastroDTO> cadastrarPet(@RequestBody @Valid CadastrarPetDTO dto, UriComponentsBuilder uriBuilder){
        var pet = service.cadastrar(dto);

        var uri = uriBuilder.path("/api/vet/pets/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPetCadastroDTO(pet));
    }

    @PutMapping("/admin/pets/{id}")
    @Transactional
    public ResponseEntity<DetalhesPetDTO> vincularVet(@PathVariable Long id, @RequestBody @Valid VincularVeterinarioDTO dto){
        var veterinario = service.vincular(dto);
        var pet = repository.getReferenceById(id);

        pet.vincular(veterinario);

        return ResponseEntity.ok().body(new DetalhesPetDTO(pet));
    }

    @PutMapping({"/tutor/pets", "/admin/pets"})
    @Transactional
    public ResponseEntity<DetalhesPetDTO> atualizarVeterinario(@RequestBody @Valid AtualizarDadosPetDTO dto){
        var veterinario = service.atualizar(dto);
        var pet = repository.getReferenceById(dto.id());

        pet.vincular(veterinario);

        return ResponseEntity.ok().body(new DetalhesPetDTO(pet));
    }

    @GetMapping("/pets")
    public ResponseEntity<Page<ListaPetsDTO>> listarPets(@PageableDefault(sort = {"nome"}) Pageable pageable){
        var page = repository.findAll(pageable).map(ListaPetsDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<DetalhesPetDTO> detalharPet(@PathVariable Long id){
        var pet = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesPetDTO(pet));
    }

    @DeleteMapping({"/admin/pets/{id}", "/tutor/pets/{id}"})
    @Transactional
    public ResponseEntity<?> deletarPet(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
