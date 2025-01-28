package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PetComConsultaNoMesmoHorario implements IValidation{

    @Autowired
    private IConsultaRepository repository;

    @Override
    public void validar(CadastrarConsultaDTO dto) {
        var primeiroHorario = dto.dataHora().withHour(6);
        var ultimoHorario = dto.dataHora().withHour(21);

        var petComConsulta = repository.existsByPetIdAndDataHoraBetween(dto.idPet(), primeiroHorario, ultimoHorario);
    }
}
