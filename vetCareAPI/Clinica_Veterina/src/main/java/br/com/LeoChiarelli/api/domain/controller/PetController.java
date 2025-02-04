package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.*;
import br.com.LeoChiarelli.api.domain.service.PetService;
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
@RequestMapping("/clinica/admin")
@SecurityRequirement(name = "bearer-key")
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping("/pets")
    @Transactional
    public ResponseEntity<DetalhesPetCadastroDTO> cadastrarPet(@RequestBody @Valid CadastrarPetDTO dto, UriComponentsBuilder uriBuilder){
        var pet = service.cadastrar(dto);

        var uri = uriBuilder.path("/api/vet/pets/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPetCadastroDTO(pet));
    }

    @PutMapping("/pets")
    @Transactional
    public ResponseEntity<DetalhesPetDTO> atualizarVeterinario(@RequestBody @Valid AtualizarDadosPetDTO dto){

        return ResponseEntity.ok().body(service.atualizarVinculo(dto));
    }

    @GetMapping("/pets")
    public ResponseEntity<Page<ListaPetsDTO>> listarPets(@PageableDefault(sort = {"nome"}) Pageable pageable){

        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<DetalhesPetDTO> detalharPet(@PathVariable Long id){

        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping("/pets/{id}")
    @Transactional
    public ResponseEntity<?> deletarPet(@PathVariable Long id){

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
