package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record EfetuarLoginDTO(

        @NotBlank
        String email,

        @NotBlank
        String senha
) {
}
