package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Pagamento;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.StatusPagamento;

public record ListaPagamentosDTO(Long id, StatusPagamento statusPagamento, String titular, Long idConsulta, Long idMetodoDePagamento) {
    public ListaPagamentosDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getStatus(), pagamento.getTitularCartao(), pagamento.getConsultaId(), pagamento.getMetodoDePagamentoId());
    }
}
