package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.AtualizarDadosPetDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarPetDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesPetDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaPetsDTO;
import br.com.LeoChiarelli.api.domain.model.Pet;
import br.com.LeoChiarelli.api.domain.repository.IPetRepository;
import br.com.LeoChiarelli.api.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.api.domain.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
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

    @Autowired
    private ITutorRepository tutorRepository;

    public Pet cadastrar(CadastrarPetDTO dto) {

        var veterinario = vetRepository.getReferenceById(dto.veterinario_id());
        var tutor = tutorRepository.getReferenceById(dto.tutor_id());
        var pet = new Pet(dto, veterinario, tutor);
        repository.save(pet);
        return pet;
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
