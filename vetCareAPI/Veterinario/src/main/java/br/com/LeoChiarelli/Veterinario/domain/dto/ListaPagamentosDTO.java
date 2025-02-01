package br.com.LeoChiarelli.Veterinario.domain.dto;

import br.com.LeoChiarelli.Veterinario.domain.model.Pagamento;
import br.com.LeoChiarelli.Veterinario.domain.model.StatusPagamento;

public record ListaPagamentosDTO(Long id, StatusPagamento statusPagamento, String titular, Long consulta, Long metodoDePagamento) {
    public ListaPagamentosDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getStatus(), pagamento.getTitularCartao(), pagamento.getConsulta().getId(), pagamento.getMetodoDePagamento().getId());
    }
}
