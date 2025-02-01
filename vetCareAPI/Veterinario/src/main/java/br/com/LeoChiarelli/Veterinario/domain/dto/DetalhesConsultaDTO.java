package br.com.LeoChiarelli.Veterinario.domain.dto;

import br.com.LeoChiarelli.Veterinario.domain.model.Consulta;
import br.com.LeoChiarelli.Veterinario.domain.model.Status;

import java.time.LocalDateTime;

public record DetalhesConsultaDTO(
        Long id,
        String pet,
        String veterinario,
        LocalDateTime dataHora,
        String detalhes,
        Status status
) {
    public DetalhesConsultaDTO(Consulta consulta){
        this(consulta.getId(), consulta.getPet().getNome(), consulta.getVeterinario().getNome(), consulta.getDataHora(), consulta.getDetalhes(), consulta.getStatus());
    }
}
