package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizarInfoTutorDTO(
        @NotBlank
        String cpf,

        @Pattern(regexp = "\\d{11,18}")
        String telefone,

        @Email
        String email,

        @Valid
        EnderecoDTO endereco
) {
}
