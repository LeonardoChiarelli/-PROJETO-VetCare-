package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AtualizarConsultaDTO(

        @NotNull
        Long id,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora
) {
}
