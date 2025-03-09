package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastrarPagamentoDTO(
        Long id,

        @NotNull
        BigDecimal valor,

        @NotBlank
        String titularCartao,

        @NotBlank
        @Size(min = 16, max = 16)
        String numeroCartao,

        @NotBlank
        @Size(max = 5)
        String expiracaoCartao,

        @NotBlank
        @Size(min = 3, max = 3)
        String codigoSeguranca,

        @NotNull
        Long idPedido,

        @NotNull
        Long idMetodo
) {
}
