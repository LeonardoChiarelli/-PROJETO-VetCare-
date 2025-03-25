package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.RelatorioEstoqueDTO;
import br.com.LeoChiarelli.api.domain.dto.RelatorioFaturamentoDTO;
import br.com.LeoChiarelli.api.domain.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loja/admin/relatorio/")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/estoque")
    public ResponseEntity<RelatorioEstoqueDTO> obterInfoEstoque(){
        var relatorio = service.infoEstoque().join();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/faturamento")
    public ResponseEntity<RelatorioFaturamentoDTO> obterInfoFaturamento(){
        var relatorio = service.infoFaturamento().join();
        return ResponseEntity.ok(relatorio);
    }
}
