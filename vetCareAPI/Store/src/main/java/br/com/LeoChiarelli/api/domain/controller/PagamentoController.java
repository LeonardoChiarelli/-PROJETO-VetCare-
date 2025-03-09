package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPagamentoDTO;
import br.com.LeoChiarelli.api.domain.dto.PagamentoCanceladoDTO;
import br.com.LeoChiarelli.api.domain.dto.PagamentoNegadoDTO;
import br.com.LeoChiarelli.api.domain.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/loja/admin")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping("/realizar")
    @Transactional
    public ResponseEntity<DetalhesPagamentoDTO> realizarPagamento(@RequestBody @Valid CadastrarPagamentoDTO dto, UriComponentsBuilder uriBuilder){
        var pagamento = service.cadastrar(dto);

        var uri = uriBuilder.path("/detalhes/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPagamentoDTO(pagamento));
    }

    @PatchMapping("/{id}/aprovar")
    @Transactional
    public ResponseEntity<DetalhesPagamentoDTO> aprovarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(new DetalhesPagamentoDTO(service.aprovarPagamento(id)));
    }

    @PatchMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<PagamentoCanceladoDTO> cancelarPagamento(@PathVariable Long id){
        return ResponseEntity.status(400).body(new PagamentoCanceladoDTO(service.cancelarPagamento(id)));
    }

    @PatchMapping("/{id}/negar")
    @Transactional
    public ResponseEntity<PagamentoNegadoDTO> negarPagamento(@PathVariable Long id){
        return ResponseEntity.status(401).body(new PagamentoNegadoDTO(service.negarPagamento(id)));
    }
}
