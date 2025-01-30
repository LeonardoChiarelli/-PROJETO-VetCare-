package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;

import java.time.LocalDateTime;

public record DetalhesConsultaDTO(
        Long id,
        String pet,
        String veterinario,
        LocalDateTime dataHora,
        String detalhes
) {
    public DetalhesConsultaDTO(Consulta consulta){
        this(consulta.getId(), consulta.getPet().getNome(), consulta.getVeterinario().getNome(), consulta.getDataHora(), consulta.getDetalhes());
    }
}
