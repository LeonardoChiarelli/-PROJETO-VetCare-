package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.MetodoDePagamento;
import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.Pedido;

import java.math.BigDecimal;

public record PagamentoNegadoDTO(Long id, BigDecimal valor, MetodoDePagamento metodo, Pedido pedido) {
    public PagamentoNegadoDTO(Pagamento p){
        this(p.getId(), p.getValor(), p.getMetodoDePagamento(), p.getPedido());
    }
}
