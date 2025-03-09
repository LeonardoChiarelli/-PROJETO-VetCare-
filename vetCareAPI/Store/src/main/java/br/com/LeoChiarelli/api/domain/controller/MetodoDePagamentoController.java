package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarMetodoDePagamentoDTO;
import br.com.LeoChiarelli.api.domain.service.MetodoDePagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loja/admin")
public class MetodoDePagamentoController{

    @Autowired
    private MetodoDePagamentoService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrarMetodo(@RequestBody @Valid CadastrarMetodoDePagamentoDTO dto){

        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<String> desativarMetodo(@PathVariable Long id){
        service.desativar(id);
        return ResponseEntity.ok("Método de pagamento desativado.");
    }
}

