package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.DetalhesVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.ListaVeterinariosDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.VeterinarioService;
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
@RequestMapping("/api/vet/veterinarios")
public class VeterinarioController {

    @Autowired
    private IVeterinarioRepository repository;

    @Autowired
    private VeterinarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarVeterinario(@RequestBody @Valid CadastrarVeterinarioDTO dto, UriComponentsBuilder uriBuilder){
        var veterinario = service.cadastrar(dto);

        var uri = uriBuilder.path("/api/vet/veterinarios/{id}").buildAndExpand(veterinario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesVeterinarioDTO(veterinario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarInfo(@RequestBody @Valid AtualizarDadosVeterinarioDTO dto){
        var veterinario = service.atualizar(dto);

        return ResponseEntity.ok().body(new DetalhesVeterinarioDTO(veterinario));
    }

    @GetMapping
    public ResponseEntity<Page<ListaVeterinariosDTO>> listaVeterinarios(@PageableDefault(sort = {"especialidade"}) Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(ListaVeterinariosDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var veterinario = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesVeterinarioDTO(veterinario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
