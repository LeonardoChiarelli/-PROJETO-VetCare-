package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Especie;
import jakarta.validation.Valid;
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
        LocalDate dataAniversario,

        @NotNull
        @Valid
        VeterinarioDTO veterinario

        //@NotNull
        //Tutor tutor
) {
}
