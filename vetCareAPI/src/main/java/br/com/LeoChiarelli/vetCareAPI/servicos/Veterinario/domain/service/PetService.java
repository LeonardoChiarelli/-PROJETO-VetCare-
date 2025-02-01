package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.service;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.*;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Pet;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IPetRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public DetalhesPetDTO vincular(Long id, @Valid VincularVeterinarioDTO dto) {
        var veterinario = vetRepository.findById(dto.id()).orElseThrow(() -> new ValidacaoException("Veterinário não encontrado"));
        var pet = repository.getReferenceById(id);

        pet.vincular(veterinario);
        return new DetalhesPetDTO(pet);
    }

    public DetalhesPetDTO atualizarVinculo(AtualizarDadosPetDTO dto) {
        var veterinario = vetRepository.findById(dto.veterinario()).orElseThrow(() -> new ValidacaoException("Veterinário não encontrado"));
        var pet = repository.getReferenceById(dto.id());

        pet.vincular(veterinario);
        return new DetalhesPetDTO(pet);
    }

    public Page<ListaPetsDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(ListaPetsDTO::new);
    }

    public DetalhesPetDTO detalhar(Long id) {
        return new DetalhesPetDTO(repository.getReferenceById(id));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
