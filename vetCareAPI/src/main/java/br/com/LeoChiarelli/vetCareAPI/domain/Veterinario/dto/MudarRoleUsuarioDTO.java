package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import jakarta.validation.constraints.NotNull;

public record MudarRoleUsuarioDTO(

        @NotNull
        Long idUsuario,

        @NotNull
        Long idPerfil
) {
}
