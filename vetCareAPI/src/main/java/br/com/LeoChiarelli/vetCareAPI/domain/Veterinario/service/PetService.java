package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarDadosPetDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarPetDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.VincularVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IPetRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.vetCareAPI.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private IPetRepository repository;

    @Autowired
    private IVeterinarioRepository vetRepository;

    public Pet cadastrar(CadastrarPetDTO dto) {
        /*
        boolean jaCadastrado = repository.existsByNomeAndTutor(dto.nome(), dto.tutor());

        if (jaCadastrado){ throw new ValidacaoException("Seu pet '" + dto.nome() + "' já está cadastrado Sr(a) " + dto.tutor(); }
         */

        var pet = new Pet(dto);
        repository.save(pet);
        return pet;
    }

    public Veterinario atualizar(AtualizarDadosPetDTO dto) {
        return vetRepository.findById(dto.veterinario()).orElseThrow(() -> new ValidacaoException("Veterinário não encontrado"));
    }

    public Veterinario vincular(@Valid VincularVeterinarioDTO dto) {
        return vetRepository.findById(dto.id()).orElseThrow(() -> new ValidacaoException("Veterinário não encontrado"));
    }
}
