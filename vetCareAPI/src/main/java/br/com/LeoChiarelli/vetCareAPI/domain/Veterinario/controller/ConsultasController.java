package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.*;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IConsultaRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.ConsultasService;
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
@RequestMapping("/api/vet/consultas")
public class ConsultasController {

    @Autowired
    private ConsultasService service;

    @Autowired
    private IConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesConsultaDTO> cadastrarConsulta(@RequestBody @Valid CadastrarConsultaDTO dto, UriComponentsBuilder uriBuilder){
        var consulta = service.agendar(dto);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesConsultaDTO> mudarHorario(@RequestBody @Valid AtualizarConsultaDTO dto){
        var consulta = service.atualizar(dto);

        return ResponseEntity.ok().body(consulta);
    }

    @GetMapping("/{idVeterinario}")
    public ResponseEntity<Page<ListaConsultasDTO>> listarConsultas(@PathVariable Long idVeterinario, @PageableDefault(sort = {"dataHora"})Pageable pageable){
        var page = repository.findAllByVeterinarioId(idVeterinario, pageable).map(ListaConsultasDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity detalharConsulta(@PathVariable Long id){
        var consulta = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesConsultaDTO(consulta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
