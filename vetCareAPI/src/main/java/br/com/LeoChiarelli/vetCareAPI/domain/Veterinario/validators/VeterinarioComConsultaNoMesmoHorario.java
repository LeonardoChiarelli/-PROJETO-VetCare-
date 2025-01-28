package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IConsultaRepository;
import br.com.LeoChiarelli.vetCareAPI.general.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

public class VeterinarioComConsultaNoMesmoHorario implements IValidation{

    @Autowired
    private IConsultaRepository repository;

    @Override
    public void validar(CadastrarConsultaDTO dto) {

        var veterinarioComConsulta = repository.existsByIdAndData(dto.idVeterinario(), dto.dataHora());

        if(veterinarioComConsulta) { throw new ValidacaoException("Veterinário já possui uma consulta neste horário.");}
    }
}
