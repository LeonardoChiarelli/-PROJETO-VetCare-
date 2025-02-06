package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import br.com.LeoChiarelli.api.domain.model.Porte;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarPetDTO(
        Long id,

        @NotBlank
        String nome,

        @NotNull
        @JsonProperty("dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotNull
        Especie especie,

        @NotNull
        Porte porte,

        @NotNull
        Float peso,

        @NotBlank
        String cor,

        @NotBlank
        String descricao
) {
}
