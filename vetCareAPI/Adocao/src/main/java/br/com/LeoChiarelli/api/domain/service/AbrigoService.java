package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.*;
import br.com.LeoChiarelli.api.domain.model.Abrigo;
import br.com.LeoChiarelli.api.domain.model.Pet;
import br.com.LeoChiarelli.api.domain.repository.IAbrigoRepository;
import br.com.LeoChiarelli.api.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.api.domain.repository.IPetRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private IAbrigoRepository repository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IPerfilRepository perfilRepository;

    public DetalhesAbrigoDTO cadastrar(CadastrarAbrigoDTO dto) {

        boolean jaCadastrado = repository.existsByCnpj(dto.cnpj());
        if (jaCadastrado) { throw new ValidacaoException("Abrigo já cadastrado"); }

        var perfil = perfilRepository.findById(1L).orElseThrow(() -> new ValidacaoException("Perfil não encontrado"));
        var abrigo = new Abrigo(dto, perfil);
        repository.save(abrigo);
        return new DetalhesAbrigoDTO(abrigo);
    }

    public DetalhesAbrigoDTO atualizar(AtualizarAbrigoDTO dto) {
        var abrigo = repository.findById(dto.id()).orElseThrow(() -> new ValidacaoException("ID do abrigo não encontrado."));
        abrigo.atualizarInfo(dto);

        return new DetalhesAbrigoDTO(abrigo);
    }

    public Page<ListaAbrigosDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(ListaAbrigosDTO::new);
    }

    public DetalhesAbrigoDTO detalhar(String idOuNome) {

        return new DetalhesAbrigoDTO(buscarAbrigo(idOuNome));
    }

    public Page<ListaPetsDTO> listarPets(String idOuNome, Pageable pageable) {
        var abrigo = buscarAbrigo(idOuNome);

        return petRepository.findAllByAbrigoAndStatusDisponivel(abrigo.getId(), pageable).map(ListaPetsDTO::new);
    }

    public DetalhesPetDTO detalharPet(String idOuNome, Long id) {
        var abrigo = buscarAbrigo(idOuNome);
        return new DetalhesPetDTO(petRepository.findByAbrigoAndId(abrigo, id).orElseThrow(() -> new ValidacaoException("Pet não encontrado no abrigo informado")));
    }

    public Pet cadastrarPet(@Valid CadastrarPetDTO dto, String idOuNome) {
        var abrigo = buscarAbrigo(idOuNome);
        var pet = new Pet(dto, abrigo);
        petRepository.save(pet);
        return pet;
    }

    public Abrigo buscarAbrigo(String idOuNome){
        Optional<Abrigo> abrigo;

        try {
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.findById(id);
        } catch (NumberFormatException e){
            abrigo = repository.findByNome(idOuNome);
        }

        return abrigo.orElseThrow(() -> new ValidacaoException("ID do abrigo não encontrado"));
    }
}
