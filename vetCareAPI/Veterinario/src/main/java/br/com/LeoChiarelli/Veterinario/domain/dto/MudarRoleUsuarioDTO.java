package br.com.LeoChiarelli.Veterinario.domain.dto;

import jakarta.validation.constraints.NotNull;

public record MudarRoleUsuarioDTO(

        @NotNull
        Long idUsuario,

        @NotNull
        Long idPerfil
) {
}
