package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.Pedido;

public record PagamentoCanceladoDTO(Long id, Pedido pedido) {
    public PagamentoCanceladoDTO(Pagamento p){
        this(p.getId(), p.getPedido());
    }
}
