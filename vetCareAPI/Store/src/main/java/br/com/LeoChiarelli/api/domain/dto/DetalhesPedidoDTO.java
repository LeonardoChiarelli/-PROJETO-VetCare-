package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhesPedidoDTO(Long id, LocalDateTime dataHora, List<ItensPedidosDTO> itens) {
    public DetalhesPedidoDTO(Pedido p){
        this(p.getId(), p.getDataPedido(), p.getItens().stream().map(ItensPedidosDTO::new).collect(Collectors.toList()));
    }
}
