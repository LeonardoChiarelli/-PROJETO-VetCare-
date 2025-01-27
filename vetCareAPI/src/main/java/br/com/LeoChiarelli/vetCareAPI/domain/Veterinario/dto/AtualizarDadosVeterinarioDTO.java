package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarDadosVeterinarioDTO(

        @NotNull
        Long id,

        @Pattern(regexp = "\\d{9,13}")
        String telefone,

        String email
) {
}
