package br.com.LeoChiarelli.api.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioFaturamentoDTO(BigDecimal faturamentoTotal, List<EstatisticasVendasDTO> estatisticas) {
}
