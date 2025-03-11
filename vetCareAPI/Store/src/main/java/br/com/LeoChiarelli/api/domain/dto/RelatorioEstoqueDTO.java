package br.com.LeoChiarelli.api.domain.dto;

import java.util.List;

public record RelatorioEstoqueDTO(List<DetalhesProdutoDTO> produtosAusentes) {
}
