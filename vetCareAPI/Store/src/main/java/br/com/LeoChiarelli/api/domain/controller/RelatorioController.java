package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.RelatorioEstoqueDTO;
import br.com.LeoChiarelli.api.domain.dto.RelatorioFaturamentoDTO;
import br.com.LeoChiarelli.api.domain.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/loja/admin/relatorio/")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/estoque")
    public ResponseEntity<CompletableFuture<RelatorioEstoqueDTO>> obterInfoEstoque(){
        var relatorio = service.infoEstoque();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/faturamento")
    public ResponseEntity<CompletableFuture<RelatorioFaturamentoDTO>> obterInfoFaturamento(){
        var relatorio = service.infoFaturamento();
        return ResponseEntity.ok(relatorio);
    }
}
