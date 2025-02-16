package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Consulta;
import br.com.LeoChiarelli.api.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ListaConsultasDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora,
        String pet,
        Status status
) {
    public ListaConsultasDTO(Consulta consulta){
        this(consulta.getId(), consulta.getDataHora(), consulta.getPet().getNome(), consulta.getStatus());
    }
}
