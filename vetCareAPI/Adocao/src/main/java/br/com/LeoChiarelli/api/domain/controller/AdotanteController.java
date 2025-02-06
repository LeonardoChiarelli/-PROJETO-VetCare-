package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdotanteDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesAdotanteDTO;
import br.com.LeoChiarelli.api.domain.service.AdotanteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes/adotante")
public class AdotanteController {

    @Autowired
    private AdotanteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesAdotanteDTO> cadastrar(@RequestBody @Valid CadastrarAdotanteDTO dto, UriComponentsBuilder uriBuilder){
        var adotante = service.cadastrar(dto);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(adotante);
    }


}
