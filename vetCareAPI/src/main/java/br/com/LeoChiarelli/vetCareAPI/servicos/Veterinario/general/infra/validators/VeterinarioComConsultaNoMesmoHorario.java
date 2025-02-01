package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.validators;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IConsultaRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinarioComConsultaNoMesmoHorario implements IValidation{

    @Autowired
    private IConsultaRepository repository;

    @Override
    public void validar(CadastrarConsultaDTO dto) {

        var veterinarioComConsulta = repository.existsByIdAndDataHora(dto.idVeterinario(), dto.dataHora());

        if(veterinarioComConsulta) { throw new ValidacaoException("Veterinário já possui uma consulta neste horário.");}
    }
}
