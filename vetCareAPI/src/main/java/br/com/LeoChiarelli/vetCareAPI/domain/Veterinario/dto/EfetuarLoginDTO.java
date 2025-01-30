package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import jakarta.validation.constraints.NotBlank;

public record EfetuarLoginDTO(

        @NotBlank
        String email,

        @NotBlank
        String senha
) {
}
