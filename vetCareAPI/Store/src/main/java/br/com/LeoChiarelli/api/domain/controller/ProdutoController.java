package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarProdutoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesProdutoDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaProdutosDTO;
import br.com.LeoChiarelli.api.domain.service.ProdutoService;
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
@RequestMapping("/loja")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/admin/produto/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesProdutoDTO> cadastrarProduto(@RequestBody @Valid CadastrarProdutoDTO dto, UriComponentsBuilder uriBuilder){
        var produto = service.cadastrar(dto);

        var uri = uriBuilder.path("/admin/produtos/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesProdutoDTO(produto));
    }

    @GetMapping({"/admin/produtos", "/comprador/produtos"})
    public ResponseEntity<Page<ListaProdutosDTO>> listarProdutos(@PageableDefault(sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping({"/admin/produtos/{id}", "/comprador/produtos/{id}"})
    public ResponseEntity<DetalhesProdutoDTO> detalharProduto(@PathVariable Long id){
        var produto = service.detalhar(id);

        return ResponseEntity.ok(new DetalhesProdutoDTO(produto));
    }

    @PatchMapping("/admin/produto/{id}/reativar")
    @Transactional
    public ResponseEntity<String> reativarProduto(@PathVariable Long id){
        service.reativar(id);

        return ResponseEntity.ok("Produto reativado");
    }

    @DeleteMapping("/admin/produto/{id}/desativar")
    @Transactional
    public ResponseEntity<String> desativarProduto(@PathVariable Long id){
        service.desativar(id);

        return ResponseEntity.noContent().build();
    }
}
