package br.com.LeoChiarelli.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastrarConsultaDTO(
        Long id,

        @NotNull
        Long idPet,

        @NotNull
        Long idVeterinario,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora,

        String detalhes
) {
}
