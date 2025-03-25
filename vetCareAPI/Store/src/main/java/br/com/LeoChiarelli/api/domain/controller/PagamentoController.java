package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPagamentoDTO;
import br.com.LeoChiarelli.api.domain.model.StatusPagamento;
import br.com.LeoChiarelli.api.domain.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/loja/admin/pagamento")
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
        return ResponseEntity.ok(new DetalhesPagamentoDTO(service.mudarStatusPagamento(id, StatusPagamento.APROVADO)));
    }

    @DeleteMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<?> cancelarPagamento(@PathVariable Long id){
        service.mudarStatusPagamento(id, StatusPagamento.CANCELADO);
        return ResponseEntity.badRequest().body("Pagamento cancelado, entre em contato com o seu Banco");
    }

    @PatchMapping("/{id}/negar")
    @Transactional
    public ResponseEntity<?> negarPagamento(@PathVariable Long id){
        service.mudarStatusPagamento(id, StatusPagamento.NEGADO);
        return ResponseEntity.badRequest().body("Pagamento negado, entre em contato com o seu Banco");
    }
}
