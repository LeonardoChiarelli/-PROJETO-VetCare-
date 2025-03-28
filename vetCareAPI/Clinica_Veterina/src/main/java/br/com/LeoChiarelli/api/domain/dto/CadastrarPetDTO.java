package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarPetDTO(
        Long id,

        @NotBlank
        String nome,

        @NotNull
        Especie especie,

        @NotBlank
        String raca,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataAniversario,

        @NotNull
        Long veterinario_id,

        @NotNull
        Long tutor_id
) {
}
