package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.validators;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IConsultaRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetComConsultaNoMesmoHorario implements IValidation{

    @Autowired
    private IConsultaRepository repository;

    @Override
    public void validar(CadastrarConsultaDTO dto) {
        var primeiroHorario = dto.dataHora().withHour(6);
        var ultimoHorario = dto.dataHora().withHour(21);

        var petComConsulta = repository.existsByPetIdAndDataHoraBetween(dto.idPet(), primeiroHorario, ultimoHorario);

        if (petComConsulta) { throw new ValidacaoException("Pet já possui uma consulta marcada no mesmo horário"); }
    }
}
