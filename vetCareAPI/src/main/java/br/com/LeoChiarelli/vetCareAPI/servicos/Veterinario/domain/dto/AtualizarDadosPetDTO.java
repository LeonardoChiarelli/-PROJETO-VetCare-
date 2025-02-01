package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosPetDTO(
        @NotNull
        Long id,

        Long veterinario
) {
}
