package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ListaConsultasDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora,
        String pet
) {
    public ListaConsultasDTO(Consulta consulta){
        this(consulta.getId(), consulta.getDataHora(), consulta.getPet().getNome());
    }
}
