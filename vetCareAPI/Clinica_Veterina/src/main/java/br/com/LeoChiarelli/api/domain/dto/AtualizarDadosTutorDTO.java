package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarDadosTutorDTO(

        @NotNull
        Long id,

        @Pattern(regexp = "\\d{11,17}")
        String telefone
) {
}
