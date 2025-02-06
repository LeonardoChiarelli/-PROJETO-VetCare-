package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovarAdocaoDTO(
        @NotNull
        Long id,

        @NotBlank
        String justificativa
) {
}
