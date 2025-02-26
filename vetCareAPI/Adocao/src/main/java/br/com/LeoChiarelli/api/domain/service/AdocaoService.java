package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesAdocaoDTO;
import br.com.LeoChiarelli.api.domain.dto.ReprovarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.model.Adocao;
import br.com.LeoChiarelli.api.domain.repository.IAbrigoRepository;
import br.com.LeoChiarelli.api.domain.repository.IAdocaoRepository;
import br.com.LeoChiarelli.api.domain.repository.IPetRepository;
import br.com.LeoChiarelli.api.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import br.com.LeoChiarelli.api.general.infra.strategies.IStrategy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private IAdocaoRepository repository;

    @Autowired
    private IAbrigoRepository abrigoRepository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private ITutorRepository adotanteRepository;

    @Autowired
    private List<IStrategy> validadores;

    public DetalhesAdocaoDTO solicitar(@Valid CadastrarAdocaoDTO dto) {
        var abrigo = abrigoRepository.findById(dto.idAbrigo()).orElseThrow(() -> new ValidacaoException("Id informado não corresponde à nenhum abrigo"));
        var pet = petRepository.findByAbrigoAndId(abrigo, dto.idPet()).orElseThrow(() -> new ValidacaoException("Pet não encontrado no abrigo informado"));
        var adotante = adotanteRepository.findByCpf(dto.cpfAdotante()).orElseThrow(() -> new ValidacaoException("Tutor não encotrado"));
        var adocao = new Adocao(abrigo, pet, adotante);

        validadores.forEach(v -> v.validar(dto));

        repository.save(adocao);

        return new DetalhesAdocaoDTO(adocao);
    }

    public void aprovar(Long id) {
        var adocao = repository.findById(id).orElseThrow(() -> new ValidacaoException("Adoção não encontrada"));
        var pet = adocao.getPet();
        adocao.aprovar(pet);
    }

    public String reprovar(@Valid ReprovarAdocaoDTO dto) {
        var adocao = repository.findById(dto.id()).orElseThrow(() -> new ValidacaoException("Adoção não encontrada"));
        var pet = adocao.getPet();
        adocao.reprovar(dto.justificativa(), pet);
        return adocao.getJustificativa();
    }
}
