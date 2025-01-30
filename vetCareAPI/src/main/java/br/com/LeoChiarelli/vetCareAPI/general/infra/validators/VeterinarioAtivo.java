package br.com.LeoChiarelli.vetCareAPI.general.infra.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.vetCareAPI.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinarioAtivo implements IValidation{

    @Autowired
    private IVeterinarioRepository repository;

    @Override
    public void validar(CadastrarConsultaDTO dto) {

        var veterinarioAtivo = repository.findAtivoById(dto.idVeterinario());

        if(!veterinarioAtivo){ throw new ValidacaoException("Veterinario está inativo"); }
    }
}
