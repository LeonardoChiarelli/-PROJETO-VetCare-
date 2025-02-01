package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CadastrarPagamentoDTO(
        Long id,

        @NotNull
        @Positive
        BigDecimal valor,

        @NotBlank
        @Size(max = 100)
        String titularCartao,

        @NotBlank
        @Pattern(regexp = "\\d{16,19}")
        String numeroCartao,

        @NotBlank
        @JsonFormat(pattern = "MM/YY")
        String expiracaoCartao,

        @NotBlank
        @Size(min = 3, max = 3)
        String codigoDeSeguranca,

        @NotNull
        Long consultaId,

        @NotNull
        Long metodoDePagamentoId
) {
}
