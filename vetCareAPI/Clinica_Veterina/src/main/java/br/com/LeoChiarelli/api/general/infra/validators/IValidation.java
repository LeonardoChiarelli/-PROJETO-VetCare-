package br.com.LeoChiarelli.api.general.infra.validators;

import br.com.LeoChiarelli.api.domain.dto.CadastrarConsultaDTO;

public interface IValidation {
    void validar(CadastrarConsultaDTO dto);
}
