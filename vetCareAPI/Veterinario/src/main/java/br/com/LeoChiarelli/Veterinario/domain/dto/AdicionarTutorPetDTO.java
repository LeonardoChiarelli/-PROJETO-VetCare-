package br.com.LeoChiarelli.Veterinario.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AdicionarTutorPetDTO(
        @NotNull
        Long id,

        @NotNull
        Long idPet
) {
}
