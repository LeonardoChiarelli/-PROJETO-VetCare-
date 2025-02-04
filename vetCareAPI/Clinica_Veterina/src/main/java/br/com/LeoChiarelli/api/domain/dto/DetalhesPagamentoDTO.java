package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetalhesPagamentoDTO(Long id, BigDecimal valor, String titular, StatusPagamento statusPagamento, LocalDateTime data) {
    public DetalhesPagamentoDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getValor(), pagamento.getTitularCartao(), pagamento.getStatus(), pagamento.getData());
    }
}
