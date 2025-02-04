package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPagamentoDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaPagamentosDTO;
import br.com.LeoChiarelli.api.domain.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clinica/admin")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping("/pagamentos")
    @Transactional
    public ResponseEntity<DetalhesPagamentoDTO> cadastrarPagamento(@RequestBody @Valid CadastrarPagamentoDTO dto, UriComponentsBuilder uriBuilder){
        var pagamento = service.cadastrar(dto);

        var uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPagamentoDTO(pagamento));
    }

    @PutMapping("/pagamentos/aprovar/{id}")
    @Transactional
    public ResponseEntity<DetalhesPagamentoDTO> aprovarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(service.aprovar(id));
    }

    @PutMapping("/pagamentos/cancelar/{id}")
    @Transactional
    public void cancelarPagamento(@PathVariable Long id){
        service.cancelar(id);
    }

    @GetMapping("/pagamentos/listar")
    public ResponseEntity<Page<ListaPagamentosDTO>> listarPagamentos(Pageable pageable){
        return ResponseEntity.ok(service.listarPagamentos(pageable));
    }

    @GetMapping("/pagamentos/detalhar/{id}")
    public ResponseEntity<DetalhesPagamentoDTO> detalhar(@PathVariable Long id){
        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping("/pagamentos/deletar")
    public ResponseEntity<?> deletarPagamentosCancelados(){
        service.deletar();

        return ResponseEntity.noContent().build();
    }
}
