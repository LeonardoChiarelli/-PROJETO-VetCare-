package br.com.LeoChiarelli.Veterinario.domain.service;

import br.com.LeoChiarelli.Veterinario.domain.dto.AdicionarTutorPetDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarDadosTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.DetalhesTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Tutor;
import br.com.LeoChiarelli.Veterinario.domain.repository.IPetRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    
    @Autowired
    private ITutorRepository repository;

    @Autowired
    private IPetRepository petRepository;


    public DetalhesTutorDTO cadastrar(@Valid CadastrarTutorDTO dto) {

        boolean jaCadastrado = repository.existsByCpf(dto.cpf());
        if (jaCadastrado) { throw new ValidacaoException("Tutor já cadastrado"); }

        var pet = petRepository.findById(dto.pet_id()).orElseThrow(() -> new ValidacaoException("Pet não encontrado"));


        var tutor = new Tutor(dto, pet);
        repository.save(tutor);
        return new DetalhesTutorDTO(tutor);
    }

    public DetalhesTutorDTO atualizar(Long id, @Valid AtualizarDadosTutorDTO dto) {
        var tutor = repository.findById(id).orElseThrow(() -> new ValidacaoException("Tutor não encotrado"));
        tutor.atualizar(dto);

        return new DetalhesTutorDTO(tutor);
    }

    public DetalhesTutorDTO adicionar(AdicionarTutorPetDTO dto) {

        var petExistente = petRepository.existsById(dto.idPet());
        if (!petExistente) { throw new ValidacaoException("Pet não cadastrado"); }

        var pet = petRepository.getReferenceById(dto.idPet());
        var petJaAdicionado = repository.existsByPets(pet);

        if (petJaAdicionado) { throw new ValidacaoException("Pet já adicionado"); }


        var tutor = repository.getReferenceById(dto.id());
        tutor.adicionarPet(pet);

        return new DetalhesTutorDTO(tutor);
    }

    public DetalhesTutorDTO buscarPorCPF(String cpf) {
        return new DetalhesTutorDTO(repository.findByCpf(cpf).orElseThrow(() -> new ValidacaoException("Tutor não encontrado")));
    }

    public void excluir(Long id) {
        var tutor = repository.getReferenceById(id);
        tutor.desativar();
    }
}
