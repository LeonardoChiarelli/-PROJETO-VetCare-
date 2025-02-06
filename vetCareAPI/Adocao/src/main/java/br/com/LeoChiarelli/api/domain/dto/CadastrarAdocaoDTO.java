package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarAdocaoDTO(
        Long id,

        @NotBlank
        String cpfAdotante,

        @NotNull
        Long idPet,

        @NotNull
        Long idAbrigo
) {
}
