package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.StatusPagamento;

public record ListaPagamentosDTO(Long id, StatusPagamento statusPagamento, String titular, Long consulta, Long metodoDePagamento) {
    public ListaPagamentosDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getStatus(), pagamento.getTitularCartao(), pagamento.getConsulta().getId(), pagamento.getMetodoDePagamento().getId());
    }
}
