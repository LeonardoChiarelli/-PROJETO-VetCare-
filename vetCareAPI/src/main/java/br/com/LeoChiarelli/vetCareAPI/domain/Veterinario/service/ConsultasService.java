package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.DetalhesConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IConsultaRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IPetRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators.IValidation;
import br.com.LeoChiarelli.vetCareAPI.general.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasService {

    @Autowired
    private List<IValidation> validadores;

    @Autowired
    private IConsultaRepository repository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IVeterinarioRepository veterinarioRepository;

    public DetalhesConsultaDTO agendar(@Valid CadastrarConsultaDTO dto) {

        if (!petRepository.existsById(dto.idPet())) { throw new ValidacaoException("Id do pet informado não existe !"); }
        if (!veterinarioRepository.existsById(dto.idVeterinario())) { throw new ValidacaoException("Id do veterinário informado não existe!"); }

        validadores.forEach(v -> v.validar(dto));

        var pet = petRepository.getReferenceById(dto.idPet());
        var veterinario = veterinarioRepository.getReferenceById(dto.idVeterinario());

        if (dto.detalhes() != null) {
            var consulta = new Consulta(null, pet, veterinario, dto.dataHora(), dto.detalhes());
            repository.save(consulta);
            return new DetalhesConsultaDTO(consulta);
        } else {
            var consulta = new Consulta(null, pet, veterinario, dto.dataHora(), null);
            repository.save(consulta);
            return new DetalhesConsultaDTO(consulta);
        }
    }

    public DetalhesConsultaDTO atualizar(@Valid AtualizarConsultaDTO dto) {
        var consulta = repository.getReferenceById(dto.id());
        consulta.mudarHorario(dto);

        return new DetalhesConsultaDTO(consulta);
    }
}
