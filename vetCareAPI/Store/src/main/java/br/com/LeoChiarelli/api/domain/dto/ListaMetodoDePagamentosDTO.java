package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.MetodoDePagamento;

public record ListaMetodoDePagamentosDTO(Long id, String metodo, Boolean ativo) {
    public ListaMetodoDePagamentosDTO(MetodoDePagamento metodo){
        this(metodo.getId(), metodo.getNome(), metodo.isAtivo());
    }
}
