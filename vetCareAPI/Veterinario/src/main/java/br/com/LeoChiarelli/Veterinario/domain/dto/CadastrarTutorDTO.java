package br.com.LeoChiarelli.Veterinario.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarTutorDTO(
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11,15}")
        String cpf,

        @NotBlank
        @Pattern(regexp = "\\d{11,17}")
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotNull
        Long pet_id
) {
}
