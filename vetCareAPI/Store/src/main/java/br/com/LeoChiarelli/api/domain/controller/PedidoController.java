package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPedidoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPedidoDTO;
import br.com.LeoChiarelli.api.domain.service.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping({"/admin/cadastrar", "/comprador/cadastrar"})
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarPedidoDTO dto, HttpServletRequest request){
        System.out.println(">>> Entrou no método cadastrar()");
        try {
            return ResponseEntity.ok(new DetalhesPedidoDTO(service.cadastrar(dto, request)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
