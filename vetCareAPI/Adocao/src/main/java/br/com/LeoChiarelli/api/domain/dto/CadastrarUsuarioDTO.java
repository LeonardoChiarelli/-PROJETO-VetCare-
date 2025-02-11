package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarUsuarioDTO(

        Long id,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {
}
