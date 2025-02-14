package br.com.LeoChiarelli.api.general.infra.strategies;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdocaoDTO;

public interface IStrategy {
    void validar(CadastrarAdocaoDTO dto);
}
