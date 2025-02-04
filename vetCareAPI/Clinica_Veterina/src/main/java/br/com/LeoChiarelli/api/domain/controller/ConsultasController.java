package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaConsultasDTO;
import br.com.LeoChiarelli.api.domain.service.ConsultasService;
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
public class ConsultasController {

    @Autowired
    private ConsultasService service;

    @PostMapping("/admin/consultas")
    @Transactional
    public ResponseEntity<DetalhesConsultaDTO> cadastrarConsulta(@RequestBody @Valid CadastrarConsultaDTO dto, UriComponentsBuilder uriBuilder){
        var consulta = service.agendar(dto);

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(consulta);
    }

    @PutMapping("/admin/consultas")
    @Transactional
    public ResponseEntity<DetalhesConsultaDTO> mudarHorario(@RequestBody @Valid AtualizarConsultaDTO dto){

        return ResponseEntity.ok().body(service.mudarHorario(dto));
    }

    @PutMapping({"/admin/consultas/agendar/{id}", "/vet/agendar/{id}"})
    @Transactional
    public ResponseEntity<String> confirmarConsulta(@PathVariable Long id){
        service.confirmar(id);
        return ResponseEntity.ok("Consulta confirmada");
    }

    @PutMapping({"/admin/consultas/concluir/{id}", "/vet/concluir/{id}"})
    @Transactional
    public ResponseEntity<?> concluirConsulta(@PathVariable Long id){
        service.concluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"vet/consultas/{idVeterinario}", "/admin/consultas/{idVeterinario}"})
    public ResponseEntity<Page<ListaConsultasDTO>> listarConsultas(@PathVariable Long idVeterinario, @PageableDefault(sort = {"dataHora"})Pageable pageable){

        return ResponseEntity.ok(service.listar(idVeterinario, pageable));
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<DetalhesConsultaDTO> detalharConsulta(@PathVariable Long id){

        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping({"/admin/consultas/{id}", "/tutor/consultas/{id}"})
    @Transactional
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id){

        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
