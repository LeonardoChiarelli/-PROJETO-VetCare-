package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarEstoqueDTO(

        @NotNull
        Long idProduto,

        @NotNull
        Integer quantidade
) {
}
