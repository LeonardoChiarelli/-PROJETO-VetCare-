package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarAbrigoDTO(

        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{14}")
        String cnpj,

        @NotBlank
        @Pattern(regexp = "\\d{11,18}")
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotNull
        @Valid
        EnderecoDTO endereco) {
}
