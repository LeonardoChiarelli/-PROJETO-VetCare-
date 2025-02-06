package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8,9}")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        @Size(max = 2)
        String uf,

        @NotBlank
        @Pattern(regexp = "\\d")
        String numero,

        String complemento
) {
}
