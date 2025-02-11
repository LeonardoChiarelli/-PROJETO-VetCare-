package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarTutorDTO(
        String id,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11,14}")
        String cpf,

        @NotBlank
        @Pattern(regexp = "\\d{11,18}")
        String telefone,

        @NotBlank
        @Email
        String email,

        @Valid
        @NotNull
        EnderecoDTO endereco
) {
}
