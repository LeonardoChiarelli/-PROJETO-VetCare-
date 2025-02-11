package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record EfetuarLoginDTO(

        @NotBlank
        String email,

        @NotBlank
        String senha
) {
}
