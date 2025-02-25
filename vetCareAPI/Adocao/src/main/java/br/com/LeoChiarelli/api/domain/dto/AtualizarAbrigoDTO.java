package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarAbrigoDTO(
        @NotNull
        Long id,

        @Pattern(regexp = "\\d{11}")
        String telefone,

        @Email
        String email,

        @Valid
        EnderecoDTO endereco
) {
}
