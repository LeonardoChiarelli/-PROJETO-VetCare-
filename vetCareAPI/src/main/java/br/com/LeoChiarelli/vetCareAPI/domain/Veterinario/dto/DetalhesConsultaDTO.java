package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;

import java.time.LocalDateTime;

public record DetalhesConsultaDTO(
        Long id,
        Long idPet,
        Long idVeterinario,
        LocalDateTime dataHora,
        String detalhes
) {
    public DetalhesConsultaDTO(Consulta consulta){
        this(consulta.getId(), consulta.getPet().getId(), consulta.getVeterinario().getId(), consulta.getDataHora(), consulta.getDetalhes());
    }
}
