package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.AtualizarEstoqueDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesEstoqueDTO;
import br.com.LeoChiarelli.api.domain.service.EstoqueService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loja/admin/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesEstoqueDTO>> listarProdutos(@PageableDefault(sort = {"quantidade"}) Pageable pageable){
        return ResponseEntity.ok(service.listarProdutos(pageable));
    }

    @PatchMapping("/atualizar")
    @Transactional
    public ResponseEntity<String> atualizarQuantidade(@RequestBody @Valid AtualizarEstoqueDTO dto) {
        service.atualizarEstoque(dto);
        return ResponseEntity.ok("Quantidade adicionada");
    }
}
