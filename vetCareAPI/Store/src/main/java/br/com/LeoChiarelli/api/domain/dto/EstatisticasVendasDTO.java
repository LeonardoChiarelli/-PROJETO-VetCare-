package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Categoria;

import java.math.BigDecimal;

public record EstatisticasVendasDTO(Categoria categoria, Long quantidadesVenda, BigDecimal faturamento) {
}
