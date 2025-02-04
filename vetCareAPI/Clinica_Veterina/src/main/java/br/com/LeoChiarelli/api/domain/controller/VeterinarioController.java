package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarVeterinarioDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesVeterinarioDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaVeterinariosDTO;
import br.com.LeoChiarelli.api.domain.service.VeterinarioService;
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
@RequestMapping("/clinica")
@SecurityRequirement(name = "bearer-key")
public class VeterinarioController {

    @Autowired
    private VeterinarioService service;

    @PostMapping("/admin/veterinarios")
    @Transactional
    public ResponseEntity<DetalhesVeterinarioDTO> cadastrarVeterinario(@RequestBody @Valid CadastrarVeterinarioDTO dto, UriComponentsBuilder uriBuilder){
        var veterinario = service.cadastrar(dto);

        var uri = uriBuilder.path("/api/vet/veterinarios/{id}").buildAndExpand(veterinario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesVeterinarioDTO(veterinario));
    }

    @PatchMapping("/admin/veterinarios")
    @Transactional
    public ResponseEntity<DetalhesVeterinarioDTO> atualizarInfo(@RequestBody @Valid AtualizarDadosVeterinarioDTO dto){
        var veterinario = service.atualizar(dto);

        return ResponseEntity.ok().body(new DetalhesVeterinarioDTO(veterinario));
    }

    @PatchMapping("/admin/veterinarios/{id}")
    @Transactional
    public ResponseEntity<String> ativar(@PathVariable Long id){
        service.ativar(id);

        return ResponseEntity.ok().body("Veterinário está ativo");
    }

    @GetMapping("/veterinarios")
    public ResponseEntity<Page<ListaVeterinariosDTO>> listaVeterinarios(@PageableDefault(sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/veterinarios/{id}")
    public ResponseEntity<DetalhesVeterinarioDTO> detalhar(@PathVariable Long id){

        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping("/admin/veterinarios/{id}")
    @Transactional
    public ResponseEntity<?> desativar(@PathVariable Long id){
        service.desativar(id);

        return ResponseEntity.noContent().build();
    }

}
