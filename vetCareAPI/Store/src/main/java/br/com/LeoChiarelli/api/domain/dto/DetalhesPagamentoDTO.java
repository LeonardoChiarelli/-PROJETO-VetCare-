package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetalhesPagamentoDTO(Long id, String titular, BigDecimal valor, StatusPagamento status, LocalDateTime data, String metodoDePagamento) {
    public DetalhesPagamentoDTO(Pagamento p){
        this(p.getId(), p.getTitularCartao(), p.getValor(), p.getStatus(), p.getData(), p.getMetodoDePagamento().getNome());
    }
}
