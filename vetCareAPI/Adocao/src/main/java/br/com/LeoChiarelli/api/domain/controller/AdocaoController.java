package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesAdocaoDTO;
import br.com.LeoChiarelli.api.domain.dto.ReprovarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.service.AdocaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes/adotar")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    @PostMapping("/tutor/solicitar")
    @Transactional
    public ResponseEntity<DetalhesAdocaoDTO> solicitar(@RequestBody @Valid CadastrarAdocaoDTO dto, UriComponentsBuilder uriBuilder){
        var adocao = service.solicitar(dto);

        var uri = uriBuilder.path("/detalhar/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(adocao);
    }

    @PatchMapping("/ong/aprovar/{id}")
    @Transactional
    public ResponseEntity<String> aprovar(@PathVariable Long id){
        service.aprovar(id);

        return ResponseEntity.ok("A adoção foi aprovada!");
    }

    @PatchMapping("/ong/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovarAdocaoDTO dto){
        var justificativa = service.reprovar(dto);

        return ResponseEntity.ok("Adoção reprovada, justificativa: '" + justificativa + "'");
    }
}
