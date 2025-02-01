package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import jakarta.validation.constraints.NotNull;

public record MudarRoleUsuarioDTO(

        @NotNull
        Long idUsuario,

        @NotNull
        Long idPerfil
) {
}
