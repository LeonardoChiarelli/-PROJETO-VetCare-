package br.com.LeoChiarelli.Veterinario.domain.controller;

import br.com.LeoChiarelli.Veterinario.domain.dto.AdicionarTutorPetDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarDadosTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.DetalhesTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.service.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clinica/admin")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping("/tutores")
    @Transactional
    public ResponseEntity<DetalhesTutorDTO> cadastrarTutor(@RequestBody @Valid CadastrarTutorDTO dto, UriComponentsBuilder uriBuilder){
        var tutor = service.cadastrar(dto);

        var uri = uriBuilder.path("tutores/{cpf}").buildAndExpand(dto.cpf()).toUri();

        return ResponseEntity.created(uri).body(tutor);
    }

    @PutMapping("/tutores/atualizar/{id}")
    @Transactional
    public ResponseEntity<DetalhesTutorDTO> atualizarInfo(@PathVariable Long id, @RequestBody @Valid AtualizarDadosTutorDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PutMapping("/tutores/adicionar/")
    @Transactional
    public ResponseEntity<DetalhesTutorDTO> adicionarPet(@RequestBody @Valid AdicionarTutorPetDTO dto){
        return ResponseEntity.ok(service.adicionar(dto));
    }

    @GetMapping("tutores/{cpf}")
    public ResponseEntity<DetalhesTutorDTO> buscarTutor(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarPorCPF(cpf));
    }

    @DeleteMapping("tutores/deletar/{id}")
    @Transactional
    public ResponseEntity<?> excluirTutor(@PathVariable Long id){
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
