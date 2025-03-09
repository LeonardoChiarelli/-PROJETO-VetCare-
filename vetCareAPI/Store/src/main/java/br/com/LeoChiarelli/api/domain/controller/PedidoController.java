package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPedidoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPedidoDTO;
import br.com.LeoChiarelli.api.domain.service.PedidoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loja/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesPedidoDTO> cadastrar(@RequestBody @Valid CadastrarPedidoDTO dto){
        return ResponseEntity.ok(new DetalhesPedidoDTO(service.cadastrar(dto)));
    }
}
