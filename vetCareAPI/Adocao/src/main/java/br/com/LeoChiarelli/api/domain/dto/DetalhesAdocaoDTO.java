package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Adocao;
import br.com.LeoChiarelli.api.domain.model.StatusAdocao;

import java.time.LocalDateTime;

public record DetalhesAdocaoDTO(Long id, LocalDateTime dataHora, StatusAdocao status) {
    public DetalhesAdocaoDTO(Adocao adocao){
        this(adocao.getId(), adocao.getDataHora(), adocao.getStatus());
    }
}
