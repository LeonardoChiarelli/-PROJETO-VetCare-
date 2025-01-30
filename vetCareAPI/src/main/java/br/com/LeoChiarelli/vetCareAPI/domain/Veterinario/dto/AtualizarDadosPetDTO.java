package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosPetDTO(
        @NotNull
        Long id,

        Long veterinario
) {
}
