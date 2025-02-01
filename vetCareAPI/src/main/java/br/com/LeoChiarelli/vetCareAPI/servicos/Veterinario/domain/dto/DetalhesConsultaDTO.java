package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Consulta;

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
