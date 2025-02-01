package br.com.LeoChiarelli.Veterinario.general.infra.validators;

import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarConsultaDTO;

public interface IValidation {
    void validar(CadastrarConsultaDTO dto);
}
