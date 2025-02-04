package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosPetDTO(
        @NotNull
        Long id,

        Long veterinario
) {
}
