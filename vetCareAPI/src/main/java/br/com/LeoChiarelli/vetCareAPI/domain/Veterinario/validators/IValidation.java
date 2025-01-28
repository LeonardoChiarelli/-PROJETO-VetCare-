package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;

public interface IValidation {
    void validar(CadastrarConsultaDTO dto);
}
