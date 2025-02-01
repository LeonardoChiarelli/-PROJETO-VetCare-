package br.com.LeoChiarelli.Veterinario.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosPetDTO(
        @NotNull
        Long id,

        Long veterinario
) {
}
